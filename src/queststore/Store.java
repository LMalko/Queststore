public class Store{

    private ItemCollection<Artifact> artifactsCollection;
    private CollectionIterator<Artifact> artifactIterator = artifactsCollection.getIterator();

    public Store(){
        this.artifactsCollection = Artifact.getArtifacts();

    }

    public ItemCollection<Artifact> getArtifactsCollection(){
        return this.artifactsCollection;
    }

    public Integer getTotalPay(Integer artifactID){
        while(artifactIterator.hasNext()){
            if(artifactIterator.next().id = artifactID){
                return artifactIterator.next().price;
            }
        }
        return 0;
    }

}
