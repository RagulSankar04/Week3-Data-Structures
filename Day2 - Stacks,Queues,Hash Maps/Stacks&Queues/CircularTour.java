public class CircularTour {
    
    static class PetrolPump {
        int petrol;
        int distance;

        PetrolPump(int p, int d) {
            this.petrol = p;
            this.distance = d;
        }
    }

    public static int findStartingPoint(PetrolPump[] pumps) {
        int start = 0, surplus = 0, deficit = 0;

        for (int i = 0; i < pumps.length; i++) {
            surplus += pumps[i].petrol - pumps[i].distance;

            if (surplus < 0) {
                start = i + 1;
                deficit += surplus;
                surplus = 0;
            }
        }

        return (surplus + deficit >= 0) ? start : -1;
    }

    public static void main(String[] args) {
        PetrolPump[] pumps = {
            new PetrolPump(6, 4),
            new PetrolPump(3, 6),
            new PetrolPump(7, 3)
        };

        int startPoint = findStartingPoint(pumps);
        System.out.println(startPoint);
    }
}