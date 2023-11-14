//
// Coded by Prudence Wong 2021-12-29
//
// NOTE: You are allowed to add additional methods if you need.
//
// Name:Ahmet Ata Karasu
// Student ID:201535527
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict():   o(n*p) The reason n times p is there is no statement before for loop and there is only one more if statement after the for loop it will be not n*n because of difference between of them(worst-case).The linear will be multiplied p times.
//
// evictFIFO(): o(n*p) The reason n times p is there is no statement before for loop and there is only one more if statement after the for loop it will be not n*n because of difference between of them(worst-case).The linear will be multiplied p times.
//
// evictLFU():  o(n*p+p) The reason n times p and +p is when the nested loop and the inner loop run together that will be the worst-case scenario.
//
// evictLRU():  o(n*p+p) The reason n times p and +p is when the nested loop and the inner loop run together that will be the worst-case scenario.
//

class COMP108A1Paging {


	// no eviction
	// Aim:
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		boolean found;
		for(int a=0; a < rSize;a++){
			found = false;
			for(int b=0; b < cSize; b++){
				if(cArray[b]==rArray[a]){
					found = true;
					output.hitPattern += 'h';
					output.hitCount++;

				}
			}
			if(found == false){

				output.hitPattern += 'm' ;
				output.missCount++;

			}
		}
		output.cache = arrayToString(cArray, cSize);
		return output;
	}
	// evict FIFO
	// Aim:
	// if a request is not in cache, evict the page present in cache for longest time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int i = 0;
		for (int a=0; a < rSize; a++){
			boolean found = false;
			for(int b=0; b < cSize; b++){
				if(cArray[b] == rArray[a]){
					found = true;
					output.hitPattern += 'h';
					output.hitCount++;
				}
			}
				if(found==false){
					output.hitPattern += 'm' ;
					output.missCount++;
				    cArray[i] = rArray[a];
				    i++;
				    if (i == cSize - 1){
				    	i =  i%cSize-1;
				    }
				}
				}
		

		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evict LFU
	// Aim:
	// if a request is not in cache, evict the page that is least freqently used since in the cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLFU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int a = 0;
		int x = cSize - 1;
		int m = 0;
		int frequency[] = new int[cSize];
		
		for (int b = 0; b < cSize; b++){
			frequency[b] = 1;
		}
		for (a = 0;a < rSize;a++){
			boolean found = false;
			for(int p = 0; p <cSize;p++){
				if(rArray[a] == cArray[p]){
					output.hitPattern += 'h';
					output.hitCount++;
					found = true;
					frequency[p]++;
				}
			}
			if (found == false){
				output.hitPattern += 'm';
				output.missCount++;
				int min = frequency[0];
				for (int t = 0; t < cSize; t++){
					if (min > frequency[t]){
						min = frequency[t];
						m = t;
					}
				}
				cArray[m] = rArray[a];
				frequency[m] = 1;
			}
		}
		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// evict LRU
	// Aim:
	// if a request is not in cache, evict the page that hasn't been used for the longest amount of time
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLRU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int a = 0;
		int x = cSize - 1;
		int m = 0;
		int queue[] = new int[cSize];
		
		for (int b = 0; b < cSize; b++){
			queue[b] = 1;
		}
		for (a = 0;a < rSize;a++){
			boolean found = false;
			for(int p = 0; p <cSize;p++){
				if(rArray[a] == cArray[p]){
					output.hitPattern += 'h';
					output.hitCount++;
					found = true;
					queue[p]++;
				}
			}
			if (found == false){
				output.hitPattern += 'm';
				output.missCount++;
				int min = queue[0];
				for (int t = 0; t < cSize; t++){
					if (min > queue[t]){
						min = queue[t];
						m = t;
					}
				}
				cArray[m] = rArray[a];
				queue[m] = 1;
			}
		}
		output.cache = arrayToString(cArray, cSize);
		return output;
	}

	// DO NOT change this method
	// this will turn the cache into a String
	// Only to be used for output, do not use it to manipulate the cache
	static String arrayToString(int[] array, int size) {
		String outString="";

		for (int i=0; i<size; i++) {
			outString += array[i];
			outString += ",";
		}
		return outString;
	}

}

