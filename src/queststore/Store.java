public class Store{

    private ItemCollection<Artifact> artifactsCollection;
    private CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();

    public Store(){
        this.artifactsCollection = Artifacts.getArifacts();

    }

    public ItemCollection<Artifact> getArtifactsCollection(){
        return this.artifactsCollection;
    }



}