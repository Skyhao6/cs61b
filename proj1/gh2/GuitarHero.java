package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        String keyboard="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[]strings=new GuitarString[37];
        for (int i=0;i<strings.length;i++){
            strings[i]=new GuitarString(440.0 * Math.pow(2.0,(i-24)/12));
        }

        while (true){
            int index=0;
            if (StdDraw.hasNextKeyTyped()){
                char key=StdDraw.nextKeyTyped();
                index=keyboard.indexOf(key);
                System.out.println(index);
                if (index==-1)
                    continue;
                strings[keyboard.indexOf(key)].pluck();
            }
            double sample=strings[index].sample()+strings[(index+1)%37].sample()+strings[(index+2)%37].sample()+strings[(index+24)%37].sample();
            StdAudio.play(sample);
            strings[index].tic();
            strings[(index+1)%37].tic();strings[(index+2)%37].tic();
            strings[(index+24)%37].tic();
        }
    }
}

