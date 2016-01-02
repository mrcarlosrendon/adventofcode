package advent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Two {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = null;
		
		int totalRequiredPaper = 0;
		int totalRequiredRibbon = 0;
		
		while((line = br.readLine()) != null) {			
			String[] dimens = line.split("x");
			
			if (dimens.length != 3) {
				continue;
			}
			
			int length = Integer.parseInt(dimens[0]);
			int width = Integer.parseInt(dimens[1]);
			int height = Integer.parseInt(dimens[2]);
			
			int surfaceArea = (2*length*width) + (2*width*height) + (2*height*length);
			int smallestSideArea = Math.min(length*width, Math.min(length*height, width*height));
			
			int smallestPerimeter = 2*Math.min(length+width, Math.min(length+height, width+height));
			int cubicVolume = length*width*height;
			
			totalRequiredPaper += surfaceArea + smallestSideArea;
			totalRequiredRibbon += smallestPerimeter + cubicVolume;
		}
		
		System.out.println("Total paper area required: " + totalRequiredPaper);
		System.out.println("Total ribbon length required: " + totalRequiredRibbon);
	}
}
