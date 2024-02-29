public class Nightmare implements Character{
    private String name;

    public Nightmare(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean isNightmare() {
        return true;
    }
}
