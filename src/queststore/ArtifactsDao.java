import java.sql.Connection;
import java.util.ArrayList;




public class ArtifactsDao{

    private static ItemCollection<Artifact> artifactsCollection = new ItemCollection<Artifact>("Artifacts");
    private JDBConnection databaseConnection = new JDBConnection("jdbc:sqlite:db/questStore.db");

    public void importArtifacts(){
        databaseConnection.connectToDatabase();

        ArrayList<ArrayList<String>> artifacts = databaseConnection.getArrayListFromQuery("SELECT * FROM artifacts");
        for(int i =0; i < artifacts.size(); i++){

            int id = Integer.parseInt(artifacts.get(i).get(0));
            String name = artifacts.get(i).get(1);
            int price = Integer.parseInt(artifacts.get(i).get(2));
            String category = artifacts.get(i).get(3);

            Artifact artifact = new Artifact(id, name, price, category);
            addArtifact(artifact);
            }

    }

    public void exportArtifacts(){
        databaseConnection.connectToDatabase();

        CollectionIterator<Artifact> artifactsIterator = artifactsCollection.getIterator();

        while (artifactsIterator.hasNext()) {
            Artifact artifact = artifactsIterator.next();
            databaseConnection.executeUpdateAgainstDatabase("INSERT INTO artifacts VALUES " + 
                                                                String.valueOf(artifact.getArtifactId()) + 
                                                                artifact.getArtifactName() + 
                                                                String.valueOf(artifact.getArtifactPrice()) + 
                                                                artifact.getArtifactCategory());
        }

    }

    public ItemCollection<Artifact> getArtifacts(){
        return artifactsCollection;
    }

    public void addArtifact(Artifact artifact){
        artifactsCollection.add(artifact);
                    
    }

    public void addArtifactToDatabase(Artifact artifact){
        databaseConnection.executeUpdateAgainstDatabase("INSERT INTO artifacts (name, price, category) VALUES ( " + "'" +
                                                        artifact.getArtifactName() + "', " +
                                                        String.valueOf(artifact.getArtifactPrice()) + ", '" +
                                                        artifact.getArtifactCategory() +
                                                        "')");
    }
}
