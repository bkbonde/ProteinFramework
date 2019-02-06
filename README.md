# Recon2Neo4j

<p>The Protein Framework uses the neo4j graph platform for the integration of multiple heterogeneous biological data sources (such as protein-protein interactions, pathways, drug-target interactions, disease-gene interactions, sequence similarity relationships). The integrated network allows the biological context of disease associated genes to be explored through visualisation, and information can be retrieved using the powerful neo4j CYPHER query language.</p>

## Tutorial available [here](https://www.youtube.com/embed/jld7QZ6qfXE).

## How to access the EpiGeNet Framework
The Protein Framework addresses to several categories of users:
<ol>
<li>General user (knowledgebase manager):</li>
The Protein Framework can be accessed online <a href="https://diseaseknowledgebase.etriks.org/protein/browser/"> here</a>.
	<br>A file with several Cypher query examples for the Protein Framework is available for download <a href="protein_framework_sample_queries.docx"> here</a>. These queries can be extended to accommodate specific topics of interest. <br>

	<li> Advanced biological queries:</li>
	For more advanced biological queries and/or further exploration of the results, the Protein Framework can be interrogated directly using java and R. An example of running a simple Cypher query in the Protein Framework using directly R code (via the <a href="https://cran.r-project.org/web/packages/RNeo4j/index.html">RNeo4j</a> library): download it <a href="clustering_protein_network.R">here</a>. <br>

### For developers

The Protein Framework is freely available for non-commercial purposes and the java code used to parse the data and populate the graph database is available <a href="https://github.com/ibalaur/ProteinFramework">here</a>.
	
#### Requirements

 - Java 7 
 - Maven (tested with Maven 3.5)

#### Install

After cloning the repository and getting into its directory:

`mvn clean`

`mvn install`

## How to contribute

If you have any suggestions or want to report a bug, don't hesitate to create an [issue](https://github.com/ibalaur/ProteinFramework/issues). Pull requests and all forms of contribution will be warmly welcomed.

## Please cite our paper on this work

Lysenko A.*, Roznovat I.A.*, Saqi M.*, Mazein A., Rawlings C.J. and Auffray C. (2016), <a href="http://biodatamining.biomedcentral.com/articles/10.1186/s13040-016-0102-8">Representing and querying disease networks using graph databases </a> (* Contributed equally). <a href="http://biodatamining.biomedcentral.com/about">BioData Mining</a>, pp. 9:23. DOI: 10.1186/s13040-016-0102-8.

## Contributors

Irina Balaur (Roznovat), [EISBM](http://www.eisbm.org/), Lyon, France - specified the translation rules, developed the code
Artem Lysenko, [Rothamsted Research](https://www.rothamsted.ac.uk/), Hertfordshire, UK - advice on the Neo4j functionality
Alexander Mazein, [EISBM](http://www.eisbm.org/), Lyon, France - idea, advice on the translation rules
Mansoor Saqi, [EISBM](http://www.eisbm.org/), Lyon, France - advice on the translation rules
Chris J. Rawlings, [Rothamsted Research](https://www.rothamsted.ac.uk/), Hertfordshire, UK - advice on the Neo4j functionality
Charles Auffray, [EISBM](http://www.eisbm.org/), Lyon, France - strategic advice  

## Useful links

 - [eTRIKS](https://www.etriks.org/) 
 - [Disease Maps Project](http://disease-maps.org/) 
 - [Recon2Neo4j](https://github.com/ibalaur/Recon2Neo4j)
 - [EpiGeNet](https://github.com/ibalaur/EpiGeNet)

## Acknowledgements
This work has been supported by the Innovative Medicines Initiative Joint Undertaking under grant agreement no. IMI 115446 (eTRIKS), resources of which are composed of financial contribution from the European Union’s Seventh Framework Programme (FP7/2007-2013) and EFPIA companies.


