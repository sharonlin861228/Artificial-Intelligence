
import java.util.*;
import java.io.*;

public class Chatbot {
	private static String filename = "./WARC201709_wid.txt";

	private static ArrayList<Integer> readCorpus() {
		ArrayList<Integer> corpus = new ArrayList<Integer>();
		try {
			File f = new File(filename);
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				if (sc.hasNextInt()) {
					int i = sc.nextInt();
					corpus.add(i);
				} else {
					sc.next();
				}
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File Not Found.");
		}
		return corpus;
	}

	static public void main(String[] args) {
		ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);

		if (flag == 100) {
			int w = Integer.valueOf(args[1]);
			int count = 0;
			// TODO count occurence of w
			for (int i : corpus) {
				if (w == i) {
					count++;
				}
			}
			System.out.println(count);
			System.out.println(String.format("%.7f", count / (double) corpus.size()));
		} else if (flag == 200) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			// TODO generate
			double random = n1 / (double) n2;
			ArrayList<triple> segments = new ArrayList<triple>();
			int i = 0;
			for (int index = 0; index < 4700; index++) {
				int count = 0;
				for (int w : corpus) {
					if (w == index) {
						count++;
					}

				}
				if (count != 0) {
					if (i == 0) {
						triple t = new triple(i, 0, count / (double) corpus.size(), index);
						segments.add(t);
					} else {
						triple t = new triple(i, segments.get(i - 1).r,
								segments.get(i - 1).r + count / (double) corpus.size(), index);
						segments.add(t);
					}
					i++;
				}

			}

			for (triple s : segments) {
				if (random <= s.r) {
					System.out.println(s.index);
					System.out.println(String.format("%.7f", s.l));
					System.out.println(String.format("%.7f", s.r));
					break;
				}
			}
		} else if (flag == 300) {
			int h = Integer.valueOf(args[1]);
			int w = Integer.valueOf(args[2]);
			int count = 0;
			ArrayList<Integer> words_after_h = new ArrayList<Integer>();
			// TODO
			for (int i = 0; i < corpus.size() - 1; i++) {
				if (corpus.get(i) == h && corpus.get(i + 1) == w) {
					count++;
				}
			}
			for (int u = 0; u < 4700; u++) {
				for (int i = 0; i < corpus.size() - 1; i++) {
					if (corpus.get(i) == h && corpus.get(i + 1) == u) {
						words_after_h.add(u);
					}
				}
			}

			// output
			System.out.println(count);
			System.out.println(words_after_h.size());
			System.out.println(String.format("%.7f", count / (double) words_after_h.size()));
		} else if (flag == 400) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h = Integer.valueOf(args[3]);
			// TODO
			double random = n1 / (double) n2;
			ArrayList<triple> segments = new ArrayList<triple>();
			ArrayList<Integer> words_after_h = new ArrayList<Integer>();
			for (int u = 0; u < 4700; u++) {
				for (int i = 0; i < corpus.size() - 1; i++) {
					if (corpus.get(i) == h && corpus.get(i + 1) == u) {
						words_after_h.add(u);
					}
				}
			}
			int i = 0;
			for (int index = 0; index < 4700; index++) {
				int count = 0;
				double p = prob(index, h, corpus, words_after_h.size());

				if (p != 0) {
					if (i == 0) {
						triple t = new triple(i, 0, p, index);
						segments.add(t);
					} else {
						triple t = new triple(i, segments.get(i - 1).r, segments.get(i - 1).r + p, index);
						segments.add(t);
					}
					i++;
				}

			}

			for (triple s : segments) {
				if (random <= s.r) {
					System.out.println(s.index);
					System.out.println(String.format("%.7f", s.l));
					System.out.println(String.format("%.7f", s.r));
					break;
				}
			}
		} else if (flag == 500) {
			int h1 = Integer.valueOf(args[1]);
			int h2 = Integer.valueOf(args[2]);
			int w = Integer.valueOf(args[3]);
			int count = 0;
			ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
			// TODO
			for (int i = 0; i < corpus.size() - 2; i++) {
				if (corpus.get(i) == h1 && corpus.get(i + 1) == h2 && corpus.get(i + 2) == w) {
					count++;
				}
			}
			for (int u = 0; u < 4700; u++) {
				for (int i = 0; i < corpus.size() - 2; i++) {
					if (corpus.get(i) == h1 && corpus.get(i + 1) == h2 && corpus.get(i + 2) == u) {
						words_after_h1h2.add(u);
					}
				}
			}

			// output
			System.out.println(count);
			System.out.println(words_after_h1h2.size());
			if (words_after_h1h2.size() == 0)
				System.out.println("undefined");
			else
				System.out.println(String.format("%.7f", count / (double) words_after_h1h2.size()));
		} else if (flag == 600) {
			int n1 = Integer.valueOf(args[1]);
			int n2 = Integer.valueOf(args[2]);
			int h1 = Integer.valueOf(args[3]);
			int h2 = Integer.valueOf(args[4]);
			// TODO
			double random = n1 / (double) n2;
			ArrayList<triple> segments = new ArrayList<triple>();
			ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
			for (int u = 0; u < 4700; u++) {
				for (int i = 0; i < corpus.size() - 2; i++) {
					if (corpus.get(i) == h1 && corpus.get(i + 1) == h2 && corpus.get(i + 2) == u) {
						words_after_h1h2.add(u);
					}
				}
			}
			if (words_after_h1h2.size() == 0) {
				System.out.println("undefined");
			} else {
				int i = 0;
				for (int index = 0; index < 4700; index++) {
					int count = 0;
					double p = prob_tri(index, h1, h2, corpus, words_after_h1h2.size());

					if (p != 0) {
						if (i == 0) {
							triple t = new triple(i, 0, p, index);
							segments.add(t);
						} else {
							triple t = new triple(i, segments.get(i - 1).r, segments.get(i - 1).r + p, index);
							segments.add(t);
						}
						i++;
					}

				}

				for (triple s : segments) {
					if (random <= s.r) {
						System.out.println(s.index);
						System.out.println(String.format("%.7f", s.l));
						System.out.println(String.format("%.7f", s.r));
						break;
					}
				}

			}

		} else if (flag == 700) {
			int seed = Integer.valueOf(args[1]);
			int t = Integer.valueOf(args[2]);
			int h1 = 0, h2 = 0;

			Random rng = new Random();
			if (seed != -1)
				rng.setSeed(seed);

			if (t == 0) {
				// TODO Generate first word using r
				double r = rng.nextDouble();
				ArrayList<triple> uni_segments = unigram(corpus);
				h1 = getword_by_r(r, uni_segments);
				System.out.println(h1);
				if (h1 == 9 || h1 == 10 || h1 == 12) {
					return;
				}

				// TODO Generate second word using r
				r = rng.nextDouble();
				ArrayList<triple> bi_segments = bigram(corpus, h1);
				h2 = getword_by_r(r, bi_segments);
				System.out.println(h2);
			} else if (t == 1) {
				h1 = Integer.valueOf(args[3]);
				// TODO Generate second word using r
				double r = rng.nextDouble();
				ArrayList<triple> bi_segments = bigram(corpus, h1);
				h2 = getword_by_r(r, bi_segments);
				System.out.println(h2);
			} else if (t == 2) {
				h1 = Integer.valueOf(args[3]);
				h2 = Integer.valueOf(args[4]);
			}

			while (h2 != 9 && h2 != 10 && h2 != 12) {
				double r = rng.nextDouble();
				int w = 0;
				// TODO Generate new word using h1,h2
				ArrayList<triple> tri_segments = trigram(corpus, h1, h2);
				if(tri_segments == null){
					tri_segments = bigram(corpus, h2);
				}
				w = getword_by_r(r, tri_segments);
				System.out.println(w);
				h1 = h2;
				h2 = w;
			}
		}

		return;
	}

	static double prob(int w, int h, ArrayList<Integer> corpus, int words_after_h_size) {
		int count = 0;
		for (int i = 0; i < corpus.size() - 1; i++) {
			if (corpus.get(i) == h && corpus.get(i + 1) == w) {
				count++;
			}
		}

		return count / (double) words_after_h_size;
	}

	static double prob_tri(int w, int h1, int h2, ArrayList<Integer> corpus, int words_after_h1h2_size){
	   int count = 0;
       for(int i = 0; i<corpus.size()-2; i++){
       	if(corpus.get(i) == h1 && corpus.get(i+1) == h2 && corpus.get(i+2) == w){
       		count ++;
       	}
       }
       
       return count/(double)words_after_h1h2_size;
   }

	static ArrayList<triple> unigram(ArrayList<Integer> corpus){
	   ArrayList<triple> segments = new ArrayList<triple>();
       int i = 0;
       for(int index=0; index<4700; index++){
    	   int count = 0;
    	   for(int w : corpus){
           		if(w == index){
           		count ++;
           		}
      
    	   }
       		if(count != 0){
       			if(i==0){
       				triple t = new triple(i, 0, count/(double)corpus.size(), index);
       				segments.add(t);
       			}
	       		else{
	       		triple t = new triple(i, segments.get(i-1).r, segments.get(i-1).r + count/(double)corpus.size(), index);
	       		segments.add(t);
	       		}
	       		i++;
       		}

       }
       return segments;
	}
	static ArrayList<triple> bigram(ArrayList<Integer> corpus, int h1){
		ArrayList<triple> segments = new ArrayList<triple>();
		ArrayList<Integer> words_after_h = new ArrayList<Integer>();
		for (int u = 0; u < 4700; u++) {
			for (int i = 0; i < corpus.size() - 1; i++) {
				if (corpus.get(i) == h1 && corpus.get(i + 1) == u) {
					words_after_h.add(u);
				}
			}
		}
		int i = 0;
		for (int index = 0; index < 4700; index++) {
			double p = prob(index, h1, corpus, words_after_h.size());

			if (p != 0) {
				if (i == 0) {
					triple t = new triple(i, 0, p, index);
					segments.add(t);
				} else {
					triple t = new triple(i, segments.get(i - 1).r, segments.get(i - 1).r + p, index);
					segments.add(t);
				}
				i++;
			}

		}
		return segments;
		}
	static ArrayList<triple> trigram(ArrayList<Integer> corpus, int h1, int h2){
		ArrayList<triple> segments = new ArrayList<triple>();
		ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
		for (int u = 0; u < 4700; u++) {
			for (int i = 0; i < corpus.size() - 2; i++) {
				if (corpus.get(i) == h1 && corpus.get(i + 1) == h2 && corpus.get(i + 2) == u) {
					words_after_h1h2.add(u);
				}
			}
		}
		if (words_after_h1h2.size() == 0) {
			System.out.println("undefined");
		} else {
			int i = 0;
			for (int index = 0; index < 4700; index++) {
				int count = 0;
				double p = prob_tri(index, h1, h2, corpus, words_after_h1h2.size());

				if (p != 0) {
					if (i == 0) {
						triple t = new triple(i, 0, p, index);
						segments.add(t);
					} else {
						triple t = new triple(i, segments.get(i - 1).r, segments.get(i - 1).r + p, index);
						segments.add(t);
					}
					i++;
				}

			}
		}
		return segments;

		}
	static int getword_by_r(double R, ArrayList<triple> segments){
		for (triple s : segments) {
			if (R <= s.r) {
				return s.index;
			}
		}
		return -1;
	}
}
	class triple {
		int i;
		double l;
		double r;
		int index;
		triple(int i, double l, double r, int index){
			this.i = i;
			this.l = l;
			this.r = r;
			this.index = index;
		}
	
	}
