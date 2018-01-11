import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.FileWriter;


public class ArtifactsDao{

    private static ItemCollection<Artifact> artifactsCollection = new ItemCollection<>("Artifacts");

    public void importArtifacts(){
        String fileName = "csv/ArtifactsDao.csv";

        try{
            BufferedReader buffer_reader = new BufferedReader(new FileReader(fileName));
            String row;
            while((row = buffer_reader.readLine()) != null){
                String[] parts = row.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int price = Integer.parseInt(parts[2]);
                String category = parts[3];
                Artifact artifact = new Artifact(id, name, price, category);
                addArtifact(artifact);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void exportArtifacts(){

        CollectionIterator<Artifact> artifactsIterator = artifactsCollection.getIterator();

        try{
            BufferedWriter br = new BufferedWriter(new FileWriter("csv/ArtifactsDao.csv"));
            StringBuilder sb = new StringBuilder();

            while (artifactsIterator.hasNext()) {
                Artifact artifact = artifactsIterator.next();
                sb.append(artifact.getArtifactId());
                sb.append(",");
                sb.append(artifact.getArtifactName());
                sb.append(",");
                sb.append(artifact.getArtifactPrice());
                sb.append(",");
                sb.append(artifact.getArtifactCategory());
                sb.append("\n");
            }

            br.write(sb.toString());
            br.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public ItemCollection<Artifact> getArtifacts(){
        importArtifacts();
        return artifactsCollection;
    }

    public void addArtifact(Artifact artifact){
        artifactsCollection.add(artifact);
    }
}
