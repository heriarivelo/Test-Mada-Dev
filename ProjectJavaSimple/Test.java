public class Test {
    public string verification_n(int n) {
        if(n%3==0) {
            return "Fluzz";
        } else if (n%5 == 0) {
            return "Gratz";
        } else if (n%5 == 0 && n%3 == 0) {
            return "fluzzGratz";
        }
        String strN = String.valueOf(n);
        return strN;
    }
}