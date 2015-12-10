/**

 * Licensed to Neo Technology under one or more contributor

 * license agreements. See the NOTICE file distributed with

 * this work for additional information regarding copyright

 * ownership. Neo Technology licenses this file to you under

 * the Apache License, Version 2.0 (the "License"); you may

 * not use this file except in compliance with the License.

 * You may obtain a copy of the License at

 *

 * http://www.apache.org/licenses/LICENSE-2.0

 *

 * Unless required by applicable law or agreed to in writing,

 * software distributed under the License is distributed on an

 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY

 * KIND, either express or implied. See the License for the

 * specific language governing permissions and limitations

 * under the License.

 */

package proteinFramework;

import java.io.File;
import java.io.IOException;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.EmbeddedGraphDatabase;

public class EmbeddedGraph
{
	// START SNIPPET: vars
	private static final String DB_PATH = "target/neo4j-db-protein_network";
	private static GraphDatabaseService graphDb;

	private ProteinNetwork protNet = new ProteinNetwork();
	
	private long start = 0, end = 0;

	// END SNIPPET: vars

	// START SNIPPET: createReltype
	public static enum RelTypes implements RelationshipType {
		eNoEvent, IN_PATHWAY, BIOMARKER, THERAPEUTIC, GENETIC_VARIATION, KANEKO_ASSOCIATED, PPI_ASSOCIATION, PPI_COLOCALIZATION, PPI_GENETIC_INTERACTION, PPI_PREDICTED_INTERACTION, TISSUE_ENHANCED, DRUG_TARGET, DRUG_ENZYME, DRUG_TRANSPORTER, DRUG_CARRIER, PART_OF, DEG_RELATED_TO, SEQ_SIM
	}

	public static enum LabelTypes implements Label {
		Pathway, Disease, Protein, Tissue, Drug, GEOStudy, GEOComparison, Gene
	}

	public static GraphDatabaseService getGraphInstance() {
		return graphDb;
	}

	// END SNIPPET: createReltype

	public static void main(final String[] args) {
		EmbeddedGraph proteinFrameworkGraphDB = new EmbeddedGraph();
		proteinFrameworkGraphDB.createDb();
		proteinFrameworkGraphDB.shutDown();
	}

	@SuppressWarnings("deprecation")
	void createDb() {
		deleteFileOrDirectory(new File(DB_PATH));

		// START SNIPPET: startDb
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		registerShutdownHook(graphDb);
		// END SNIPPET: startDb

		// START SNIPPET: transaction
		try (Transaction tx = graphDb.beginTx()) {
			// Database operations go here
			// END SNIPPET: transaction

			// START SNIPPET: addData
			try {

				start = System.currentTimeMillis();
				System.out.println("readPathwayInfo");
				protNet.readPathwayInfo();
				System.out.println("readDataFromIntact");
				protNet.readDataFromIntact();
				System.out.println("readHumanTissueAtlasInfo");
				protNet.readHumanTissueAtlasInfo();
				System.out.println("readDataFromDisGeNETFile");
				protNet.readDataFromDisGeNETFile();
				System.out.println("readDataFromKanekoPaper");
				protNet.readDataFromKanekoPaper();
				System.out.println("readDrugInfo");
				protNet.readDrugInfo();
				System.out.println("readGEOStudyInfo");
				protNet.readGEOStudyInfo();
				System.out.println("addSeqSimilarityInfo");
				protNet.addSeqSimilarityInfo();

				end = System.currentTimeMillis();
				long diff = end - start;
				System.out.println("Time (in seconds) is : " + diff * 0.001);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			tx.success();
		}
		// END SNIPPET: transaction

	}

	void shutDown() {
		System.out.println();
		System.out.println("Shutting down database ...");

		// START SNIPPET: shutdownServer

		graphDb.shutdown();
		// END SNIPPET: shutdownServer
	}

	// START SNIPPET: shutdownHook

	private static void registerShutdownHook(final GraphDatabaseService graphDb) {

		// Registers a shutdown hook for the Neo4j instance so that it shuts
		// down nicely when the VM exits (even if you "Ctrl-C" the

		// running application).

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});

	}

	// END SNIPPET: shutdownHook
	private static void deleteFileOrDirectory(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				for (File child : file.listFiles()) {
					deleteFileOrDirectory(child);
				}
			}
			file.delete();
		}
	}
}
