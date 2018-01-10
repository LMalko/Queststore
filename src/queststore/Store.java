public class Store{

    private ItemCollection<Artifact> artifactsCollection;

    public Store(){

    }

    public ItemCollection<Artifact> getArtifactsCollection(){
        return this.artifactsCollection;
    }
}