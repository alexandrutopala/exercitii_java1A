public class Punct {
    int x;
    int y;
    
    Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    int getDistance(Punct p) {
        return (int) Math.sqrt(
            Math.pow(this.x - p.x, 2) +
            Math.pow(this.y - p.y, 2)
        );
    }
}