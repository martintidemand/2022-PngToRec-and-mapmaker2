import java.util.ArrayList;
import java.util.HashMap;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
//HashMap<String, String> capitalCities = new HashMap<String, String>();
//wait, there are 2 pictures, 


//Initialization:



public class PngToRec{
	public static HashMap<Color,Biome> biomes = new HashMap<Color, Biome>();
	public static Raster himage;
	public static Raster bimage;
	public static int h;
	public static int w;
	public static HashMap<Color,Double> listheights = new HashMap<Color,Double>();// = new Color[10];
	public static HashMap<Color,String> listbiomes = new HashMap<Color,String>();// = new Color[15];

	public void assignColor(){
		int[][] heightcolors = {{41,105,40},{87,183,249},{213,207,206},{114,170,47},{150,101,41},{160,211,59},{191,142,31},{201,229,126},{211,181,68},
			{223,205,111},{249,243,194}};
		//int[] realHeights = {1,0,10,2,9,3,8,4,7,6,5};
		double[] realHeights = {0.6,0.4,11.8,1.0,9.8,1.4,7.8,2.2,5.8,3.8,3.0};
		int[][] biomecolors = {{58,125,21},{40,127,71},{31,128,29},{91,154,76},{52,142,64},  {75,151,75},{215,197,154},{148,190,129},{91,154,76},
			{124,156,107},{164,189,71},{255,204,153},{193,190,66},{44,101,29},{164,189,71},{255,255,255}};
		String[] realBiomes = {"Tropical Rainforest","Taiga/Boreal Forest","Subtropical Rainforest","Montane Forest","Subtropical Dry Forest","Scrub/Mediterranean Forest","Arid Desert","Cold Stepe","Temperate Broadleaf Forest",
			"Alpine Tundra","? Savanna","Xeric Shrubland","Semiarid Desert","Mangrove Swamp","Temperate Steppe","Remove"};

		int i = 0;
		while(i < 11){
			int j = 0;
			while(j < 16){
				listheights.put(new Color(heightcolors[i],biomecolors[j]),realHeights[i]);
				listbiomes.put(new Color(heightcolors[i],biomecolors[j]),realBiomes[j]);
				j = j + 1;	
			}
			i = i + 1;
		}
			

	}
	//This class tracks 1 biome
	public class Color{
		public int hr, hg, hb, br, bg, bb;

		public Color(int[] hs,int[] bs){
			this.hr = hs[0];
			this.hg = hs[1];
			this.hb = hs[2];
			//System.out.println(hs[3] + " "+ bs[3]);
			this.br = bs[0];
			this.bg = bs[1];
			this.bb = bs[2];
		}



		@Override
		public int hashCode(){
			return this.hr+100*this.hg+10000*this.hb+1000000*this.br+100000000*this.bg+1000000000*this.bb;
		}

		public String toString(){
			return "(" + this.hr + "," + this.hg + "," + this.hb + "), (" + this.br + "," + this.bg + "," + this.bb + ")";
		}

		public String bcolor(){
			return "[" + this.br + "," + this.bg + "," + this.bb + "]";
		}

		public boolean equals(Object other){
			return this.hashCode() == other.hashCode();
		}
	}

	//on new color:
	//create the biome if not already existing
	//insert a future of new rectangle
	
	//wait

	//on print, conver the future to [x,y,z,e]

	public class Biome{
		public ArrayList<Future<ArrayList<Integer>>> biome;
		public Color id;
		public double colorh;
		public String colorb;
				//recs   wait     rec       co
		public Biome(Color color,PngToRec self){
			this.biome = new ArrayList<>();
			this.id = color;
			biomes.put(color,this);

			this.colorh = self.listheights.get(color);
			this.colorb = self.listbiomes.get(color);
		}


		public void addRec(ExecutorService sched, int x, int y,PngToRec png){//int x,int y,int xe,int ye){
			Callable<ArrayList<Integer>> fun = ()-> {
				return start(x,y,this.id,png);
			};
			Future<ArrayList<Integer>> newRec = sched.submit(fun);
			this.biome.add(newRec);
		}

		public String toString(){
			String string = "[";
			try{
				for (Future<ArrayList<Integer>> i : biome) {
  					string = string + i.get().toString() + ",";
				}			
			}catch(InterruptedException | ExecutionException e){
				e.printStackTrace();
			}
			return string.substring(0,string.length()-1) + "]";
		}
	}

	//-------------------------------------------------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args){
		PngToRec main = new PngToRec();
		if (args.length == 1){
			help();
		}else{
			main.newMain();
		}
	}

	public static void help(){
		System.out.println("1. Get a height map and biome map\n"+
		"2. insert files\n"+
		"3. define heights and biomes\n"+
			"\t3.a and change their loops\n"+
		"4. Define brick colors\n"+
			"\t4.a if 2 colors are similar, give them the same biome color\n" +
		"5. Run\n"+
		"6. Insert into plugin\n"+
			"\t6.a convert all [] to {}\n"+
			"\t6.b insert , between biomes\n"+
			"\t6.c insert coords, heights and biomes\n"+
		"7. Run Plugin\n"+
			"\t7.a remember to record :)");
	}
	//I need to run again on simplified colors
	//I need to run again with accurate heights

	public Color getPixel(int x,int y){
		int[] cols = new int[4];
		int[] cols2 = new int[4];
		himage.getPixel(x,y,cols);
		bimage.getPixel(x,y,cols2);
		//System.out.println(cols[0] + " and " + cols2[0]);
		Color col = new Color(cols,cols2);
		return col;	
	}		



	//Used find close x,y
	public static boolean isStart(int x, int y, Color color, PngToRec self){
		if(x == 0 && y == 0){
			return true;
		}
		if(y == 0 && !self.getPixel(x-1,y).equals(color)){
			return true;
		}
		if(x == 0 && !self.getPixel(x,y-1).equals(color)){
			return true;
		}
		if(!self.getPixel(Math.max(x-1,0),y).equals(color) 
			&& (!self.getPixel(x,Math.max(y-1,0)).equals(color) 
		|| (self.getPixel(x,Math.max(y-1,0)).equals(color) 
		&&  self.getPixel(Math.max(x-1,0),Math.max(y-1,0)).equals(color)))){ //behind
			return true;
		}
		return false;
	}

	//Used to find far x and far y
	//different color: not a part of box
	public static boolean isColor(int x, int y, Color color, PngToRec self){
		//1 //true
		//0 //false
		if(self.getPixel(x,y).equals(color)){
			return true;
		}
		return false;
	}

	//Use to check below start
	//same color but a different box.
	public static boolean isBoxBehindStart(int x, int y, Color color, PngToRec self){
		//01 //true
		//11 //false
		if(x == 0){
			return true;
		}
		if(!self.getPixel(x-1,y).equals(color)){
			return true;
		}
		return false;
	}

	public static ArrayList<Integer> start(int x, int y,Color color,PngToRec png){
		ArrayList<Integer> coods = new ArrayList<>();
		int xe = x+1;
		while(xe < w && png.getPixel(xe,y).equals(color)){
			xe = xe + 1;
		}
		int ye = y+1;
		while(ye < h && isColor(x,ye,color,png) && isBoxBehindStart(x,ye,color,png)){
			int i = x;
			while(i < w && png.getPixel(i,ye).equals(color)){
				i = i + 1;
			}
			if(i != xe){
				coods.add(x);
				coods.add(y);
				coods.add(xe);
				coods.add(ye);
				xe = i;
				y = ye;
			}
			ye = ye + 1;
		}
		coods.add(x);
		coods.add(y);		
		coods.add(xe);
		coods.add(ye);


		return coods;
	}

	public void newMain(){
		this.assignColor();
		File hinput, binput;
		try{
			hinput = new File("hhorea.png");
			binput = new File("bhorea.png");
			BufferedImage buffh = ImageIO.read(hinput);
			BufferedImage buffb = ImageIO.read(binput);

    		BufferedImage convertedImg1 = new BufferedImage(buffh.getWidth(), buffh.getHeight(), BufferedImage.TYPE_INT_RGB);
    		convertedImg1.getGraphics().drawImage(buffh, 0, 0, null);
    		BufferedImage convertedImg2 = new BufferedImage(buffb.getWidth(), buffb.getHeight(), BufferedImage.TYPE_INT_RGB);
    		convertedImg2.getGraphics().drawImage(buffb, 0, 0, null);


			//System.out.println(buffh.)
			himage = convertedImg1.getData();
			bimage = convertedImg2.getData();
			h = bimage.getHeight();
			w = bimage.getWidth();
			Color col = this.getPixel(0,0);
			//System.out.println(col);
		}catch(IOException e){
			e.printStackTrace();
			return;
		}

		ExecutorService sched = Executors.newFixedThreadPool(Math.min(Runtime.getRuntime().availableProcessors(),16));
		


		for (int y = 0; y < h; y++) {
  			for (int x = 0; x < w; x++){
  				Color color = this.getPixel(x,y);
  				if(isStart(x,y,color,this) == true){
  					if(!biomes.containsKey(color)){
  						Biome bio = new Biome(color,this);
  					}
  					biomes.get(color).addRec(sched,x,y,this);

  				}
  			}
		}

		try{
			sched.shutdown();
			sched.awaitTermination(1, TimeUnit.DAYS);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.print(this.toString());
	}


	public String toString(){
		String toReturn = "";
		for (Biome i : biomes.values()) { //First print the biomes
			if (!i.id.bcolor().equals("[255,255,255]")){
  				toReturn = toReturn + i + ",\n";
  			}
		}
		for (Biome i : biomes.values()) { //Print  (color, color, height, biome)
  			toReturn = toReturn + i.id.toString() + "\t" + i.colorh + " " + i.colorb + "\n";
		}
		toReturn = toReturn + "["; //
		for (Biome i : biomes.values()) { //Print the heights of each
			if (!i.id.bcolor().equals("[255,255,255]")){
  				toReturn = toReturn + i.colorh+ ", ";
  			}
		}
		toReturn = toReturn.substring(0,toReturn.length()-2) + "]\n[";
		for (Biome i : biomes.values()) { //print the biomes of each
			if (i.colorh == 10){
				toReturn = toReturn + "[242, 243, 243]" + ", "; //white
			}else if(i.colorh == 9){
				toReturn = toReturn + "[105, 102, 92]" + ", "; //flint
			}else if (!i.id.bcolor().equals("[255,255,255]")){
				toReturn = toReturn + i.id.bcolor() + ", ";
			}
  			
		}
		toReturn = toReturn.substring(0,toReturn.length()-2) + "]";
		return toReturn;
	}
}
