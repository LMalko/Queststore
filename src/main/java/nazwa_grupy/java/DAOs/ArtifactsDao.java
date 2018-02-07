package nazwa_grupy.java.DAOs;

import nazwa_grupy.java.Iterator_DBProcessor.DBStatementProcessor;
import nazwa_grupy.java.Models.Artifact;
import nazwa_grupy.java.Models.ItemCollection;

import java.util.ArrayList;


public class ArtifactsDao{

    private static ItemCollection<Artifact> artifactsCollection = new ItemCollection<Artifact>("Artifacts");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");
    private static final int artifactIdIndex = 0;
    private static final int artifactNameIndex = 1;
    private static final int artifactPriceIndex = 2;
    private static final int artifactCategoryIndex = 3;


    public void importArtifacts() {

        artifactsCollection.clear();
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> artifacts = databaseProcessor.getArrayListFromQuery("SELECT * FROM artifacts");

        for(int i=0; i < artifacts.size(); i++) {
            int id = Integer.parseInt(artifacts.get(i).get(artifactIdIndex));
            String name = artifacts.get(i).get(artifactNameIndex);
            int price = Integer.parseInt(artifacts.get(i).get(artifactPriceIndex));
            String category = artifacts.get(i).get(artifactCategoryIndex);

            Artifact artifact = new Artifact(id, name, price, category);
            addArtifactToArtifactsCollection(artifact);
        }
    }

    public ItemCollection<Artifact> getArtifacts(){
        return artifactsCollection;
    }

    private void addArtifactToArtifactsCollection(Artifact artifact) {
        artifactsCollection.add(artifact);
    }

    public void addArtifactToDatabase(Artifact artifact) {
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO artifacts (name, price, category) VALUES ( " + "'" +
                artifact.getArtifactName() + "', " +
                String.valueOf(artifact.getArtifactPrice()) + ", '" +
                artifact.getArtifactCategory() +
                "')");
    }

    public void addArtifactToStudent(Artifact artifact, int StudentID) {
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO student_artifacts (artifact_id, student_id, status)" +
                "VALUES ( " + artifact.getArtifactId() + ", " +
                StudentID + ", 'not used')");
    }

    public void returnSpecifiedStudentArtifacts(int studentID) {
        databaseProcessor.executeQueryAgainstDatabase("SELECT student_artifacts.artifact_id, artifacts.name, student_artifacts.status " +
                                                        "FROM student_artifacts " +
                                                        "INNER JOIN artifacts ON student_artifacts.artifact_id = artifacts.id " +
                                                        "WHERE student_artifacts.student_id=" +
                                                        studentID + ";");
    }

    public void markGivenArtifact(int artifactID){
        databaseProcessor.executeUpdateAgainstDatabase("UPDATE student_artifacts " +
                                                        "SET status = 'used' " +
                                                        "WHERE artifact_id=" + artifactID + ";");
    }

    public void updateArtifactDataInDatabase(Artifact artifact) {
        String name = artifact.getArtifactName();
        int price = artifact.getArtifactPrice();
        String category = artifact.getArtifactCategory();

        String query = "UPDATE artifacts SET name = '" + name + "' ," +
                "price = '" + price + "' ," +
                "category = '" + category + "' " +
                "WHERE id = '" + artifact.getArtifactId() + "';";
        databaseProcessor.executeUpdateAgainstDatabase(query);
    }
}