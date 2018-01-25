import java.sql.Connection;
import java.util.ArrayList;




public class ArtifactsDao{

    private static ItemCollection<Artifact> artifactsCollection = new ItemCollection<Artifact>("Artifacts");
    private DBStatementProcessor databaseProcessor = new DBStatementProcessor("jdbc:sqlite:db/questStore.db");

    public void importArtifacts(){

        artifactsCollection.clear();
        databaseProcessor.connectToDatabase();

        ArrayList<ArrayList<String>> artifacts = databaseProcessor.getArrayListFromQuery("SELECT * FROM artifacts");
        for(int i =0; i < artifacts.size(); i++){

            int id = Integer.parseInt(artifacts.get(i).get(0));
            String name = artifacts.get(i).get(1);
            int price = Integer.parseInt(artifacts.get(i).get(2));
            String category = artifacts.get(i).get(3);

            Artifact artifact = new Artifact(id, name, price, category);
            addArtifact(artifact);
            }

    }

    public ItemCollection<Artifact> getArtifacts(){
        return artifactsCollection;
    }

    private void addArtifact(Artifact artifact){
        artifactsCollection.add(artifact);
                    
    }

    public void addArtifactToDatabase(Artifact artifact){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO artifacts (name, price, category) VALUES ( " + "'" +
                                                        artifact.getArtifactName() + "', " +
                                                        String.valueOf(artifact.getArtifactPrice()) + ", '" +
                                                        artifact.getArtifactCategory() +
                                                        "')");
    }

    public void addArtifactToStudent(Artifact artifact, int StudentID){
        databaseProcessor.executeUpdateAgainstDatabase("INSERT INTO student_artifacts (artifact_id, student_id, status)" + 
                                                       "VALUES ( " + artifact.getArtifactId() + ", " +
                                                       StudentID + ", 'bought/ not used')");
    }

    public void returnStudentArtifacts(int studentID){
        databaseProcessor.executeQueryAgainstDatabase("SELECT name from artifacts where id IN (SELECT artifact_id " + 
                                                      " from student_artifacts where student_id="
                                                      + studentID
                                                      + ");");
    }
}
