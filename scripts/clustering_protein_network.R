#This script is created based on the RNeo4j documentation, available on github: 
#https://github.com/nicolewhite/RNeo4j and Neo4j blog: https://neo4j.com/blog/visualize-graph-with-rneo4j/

library(RNeo4j)
library(gProfileR)
library(igraph)
library(visNetwork)

# Establish connection to neo4j.
neo4j = startGraph("https://diseaseknowledgebase.etriks.org/protein/db/data/", username="neo4j", password="test")

# Cypher query for the set of Asthma biomarkers related through either PPI or sequence similarity relationships based on information from the Protein Framework
edges_query_PPI="MATCH (p1:Protein)-[:SEQ_SIM|PPI_ASSOCIATION]-(p2:Protein), (p1)-[:BIOMARKER|KANEKO_ASSOCIATED]->({DiseaseName:'ASTHMA'}), (p2)-[:BIOMARKER|KANEKO_ASSOCIATED]->({DiseaseName:'ASTHMA'}) WHERE id(p1) > id(p2) RETURN p1.GeneSymbol AS from, p2.GeneSymbol AS to, COUNT(*) AS weight"

edges = cypher(neo4j, edges_query_PPI)
nodes = data.frame(id=unique(c(edges$from, edges$to)))
nodes$label = nodes$id

ig = graph_from_data_frame(edges, directed=F)
nodes$betweenness_value = betweenness(ig)

#call Girvan Newman cluster function
clusters_Girvan_Newman = cluster_edge_betweenness(ig)
#assign cluster number to node
nodes$GirvanNewmanCluster = clusters_Girvan_Newman$membership

# Visualization using the vizNetwork library
network <- visNetwork(nodes, edges)
visHierarchicalLayout(network) 

#print stats to console
cat("\nGirvan Newman clusters number: ", length(clusters_Girvan_Newman))


