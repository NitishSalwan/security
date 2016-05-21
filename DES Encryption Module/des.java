import java.util.BitSet;


public class des {

	// initial permutation table
	private int[] initial_perm = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36,
			28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32,
			24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19,
			11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 };
	
	
	// Permutation choice One
	private static int[] perm_choice_one = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34,
			26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63,
			55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53,
			45, 37, 29, 21, 13, 5, 28, 20, 12, 4 };
	
	// Permutation choice Two
	private static int[] perm_choice_two = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10,
			23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
			30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29,
			32 };
	
	
	// Expansion Permutation
	private static int[] expansion_perm = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8,
			9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21,
			20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32,
			1 };
	
	// Permutation function
	private static int[] Perm = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5,
			18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4,
			25

	};
	
	
	// Inverse Initial permutation
	private static int[] inverse_init_perm = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
				15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13,
				53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51,
				19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17,
				57, 25 };
	
	
	// DES S-Boxes
	private static int[][][] sboxes = {
			{ 		{ 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
					{ 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
					{ 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
					{ 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } 
			},
			{ 		{ 15, 1, 8, 14, 6, 11, 3, 2, 9, 7, 2, 13, 12, 0, 5, 10 },
					{ 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
					{ 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
					{ 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } 
			},
			{ 		{ 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
					{ 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
					{ 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
					{ 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } 
			},
			{ 		{ 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
					{ 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
					{ 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
					{ 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } 
			},
			{ 		{ 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
					{ 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
					{ 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
					{ 11, 8, 12, 7, 1, 14, 2, 12, 6, 15, 0, 9, 10, 4, 5, 3 } 
			},
			{ 		{ 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
					{ 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
					{ 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
					{ 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }

			},
			{ 		{ 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
					{ 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
					{ 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
					{ 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }

			},
			{ 		{ 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
					{ 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
					{ 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
					{ 2, 1, 14, 7, 4, 10, 18, 13, 15, 12, 9, 0, 3, 5, 6, 11 }

			} };

	
	
	/*
	 * s_box_work (BitSet)
	 * 
	 * Description:
	 * This function takes 48 bits bitset as input and returns 32 bits bitset
	 * where we use different 8 S-Boxes to map 48 bits to 32 bits bitset.
	 * It maps 8 pieces.Each piece(6 bits) to 4 bits.
	 * 
	 * Arguments:
	 * start(int) 			: start bit of piece
	 * last (int) 			: last bit of piece
	 * xor_data_key(BitSet)
	 * after_s_box
	 * s_box_num
	 * afte_s_box_indx
	 * 
	 * Returns :
	 * after_s_box (Bitset) (where only 32 bits are significant)
	 * 
	 */
	
	public static BitSet s_box_work(int start,int last,BitSet xor_data_key,BitSet after_s_box,int s_box_num,int afte_s_box_indx)
	{
		
		int row= Integer.parseInt("" + (xor_data_key.get(start)?1:0) + (xor_data_key.get(last)?1:0),2);
		System.out.println(row);
		int col = Integer.parseInt("" + (xor_data_key.get(start+1)?1:0) + (xor_data_key.get(start+2)?1:0) + (xor_data_key.get(start+3)?1:0) + (xor_data_key.get(start+4)?1:0),2);
		System.out.println(col);
		String b=String.format("%4s", Integer.toBinaryString(sboxes[s_box_num][row][col])).replace(' ','0');
		System.out.println(b);
		for(int u=0;u<=3;u++)
		{
			if(b.charAt(u)=='1')
				after_s_box.set(afte_s_box_indx+u);
		}
		return after_s_box;
		
	}
			
	
	/*
	 * leftShiftBitSet (BitSet)
	 * 
	 * Description:
	 * This function executes the left shift bit operation on BitSet
	 * 
	 * Argument:
	 * bitSet (BitSet) : the bitset that needs to be left shift
	 * 
	 * Returns:
	 * The left shifted BitSet
	 *
	 *
	 * Reference  : stackoverflow.com
	 *
	 */
	
	
	public static BitSet leftShiftBitSet(BitSet bitSet) {
	    final long maskOfCarry = 0x8000000000000000L;
	    long[] aLong = bitSet.toLongArray();

	    boolean carry = false;
	    for (int i = 0; i < aLong.length; ++i) {
	        if (carry) {
	            carry = ((aLong[i] & maskOfCarry) != 0);
	            aLong[i] <<= 1;
	            ++aLong[i];
	        } else {
	            carry = ((aLong[i] & maskOfCarry) != 0);
	            aLong[i] <<= 1;
	        }
	    }

	    if (carry) {
	        long[] tmp = new long[aLong.length + 1];
	        System.arraycopy(aLong, 0, tmp, 0, aLong.length);
	        ++tmp[aLong.length];
	        aLong = tmp;
	    }

	    return BitSet.valueOf(aLong);
	}
	
	
	/*
	 * 
	 * Permutation (BitSet)
	 * 
	 * 
	 * Description:
	 * This function takes a Bitset and replace the bit values according to the value_array 
	 * 
	 * Arguments:
	 * bitset(BitSet) 		: on which permutation will execute
	 * value_array (int []) : According to which Bitset will change  
	 * size (int)           : Till what size Bitset should be changed
	 * 
	 * Returns :
	 * bitset_return2 (BitSet) : Permuted bitset
	 */
	
	public BitSet permutation(BitSet bitset,int[] value_array,int size)
	{
		BitSet bitset_return2 = new BitSet(64);
		for(int i=0;i<size;i++)
		{
			if(bitset.get(value_array[i]-1))
				bitset_return2.set(i);			
		}
		return bitset_return2;
	}
	
	
	/*
	 * xor (BitSet,BitSet)
	 * 
	 * 
	 * Description:
	 * Execute  Bitwise XOR operation on given two Bitsets 
	 * 
	 * Arguments:
	 * a (BitSet) 
	 * b (BitSet)
	 * 
	 * Returns:
	 * XORed BitSet
	 * 
	 */
	
	public BitSet xor(BitSet a,BitSet b)
	{
		a.xor(b);
		return a;		
	}
	
	
	
	public static void main(String[] args)
	{
		String input_str="";
		byte[] input=new byte[8];
		for(int i=0;i<3;i++)
			input[i]=(byte) 155;
		input[3]=(byte)255;
		input[4]=(byte)152;
		input[5]=(byte)172;
		for(int i=6;i<8;i++)
			input[i]=(byte) 0;
		BitSet ab_1=BitSet.valueOf(input);
		
		System.out.println("Input Data:");
		
		
		for(int i=0;i<ab_1.size();i++)
		{
			if(i==32)
			{System.out.print(" ");}
			
			if(ab_1.get(i))
			{
			System.out.print(1);
			input_str+="1";
			}
			else
			{
			System.out.print(0);
			input_str+="0";
			}
		}
		System.out.println("");
		
		
		
		
		System.out.println("key:");
		
		
		for(int i=0;i<ab_1.size();i++)
		{
			if(i==32)
			{System.out.print(" ");}
			
			if(ab_1.get(i))
			{
			System.out.print(1);
			input_str+="1";
			}
			else
			{
			System.out.print(0);
			input_str+="0";
			}
		}
		System.out.println("");
		System.out.println("");
		
		
		
		des des_object=new des();
		
		
		
		/*
		 * 
		 * DATA BLOCK operations
		 * 
		 * 
		 * 
		 * Initial Permutation for data block
		 * Returns 64 bit
		 */		
		BitSet perm_data_block=des_object.permutation(ab_1,des_object.initial_perm,64);
		for(int i=0;i<perm_data_block.size();i++)
		{
			if(perm_data_block.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		
		BitSet perm_data_block_left= new BitSet(32);
		BitSet perm_data_block_right= new BitSet(32);
		
		
		System.out.println();
		
		
		
		for(int i=0;i<32;i++)
		{
			if(perm_data_block.get(i))
			{
				perm_data_block_left.set(i);
				System.out.print(1);
			}
			else
			System.out.print(0);
		}
		
		for(int i=32;i<64;i++)
		{
			if(perm_data_block.get(i))
			{
				perm_data_block_right.set(i-32);
				System.out.print(1);
			}
			else
			System.out.print(0);
		}
		
		
		
		
		//start 16 rounds of encryption
		
		for(int s=0;s<16;s++)
		{
		
			System.out.println();
			System.out.println();
			System.out.println(s+1 + "th rounding starting");
			
		/*
		 * We will expand_data_right which results from 
		 * Expansion function from Right 32 bits of perm_data_block
		 * returning 48 bit
		 *
		 */	
			
		BitSet expand_data_right=des_object.permutation(perm_data_block_right,des_object.expansion_perm,48);
		for(int i=0;i<48;i++)
		{
			if(expand_data_right.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		
		System.out.println();
		

		/*
		 * 
		 * KEY Block operations
		 * 
		 * 
		 * Permuted choice One for 64 bit key
		 * Returns 64 bit but use only first 56 bits.
		 * Perm_key_block_1 comes after PC1 	
		 *
		 */
		BitSet perm_key_block_1=des_object.permutation(ab_1,des_object.perm_choice_one,56);
		for(int i=0;i<56;i++)
		{
			if(i==28)
			{System.out.print(" ");}
			
			if(perm_key_block_1.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		
		
		System.out.println("");
		
		
		
		
		/*
		 * left shifts of key...28 28 bits  
		 */
		
		BitSet key_left=des.leftShiftBitSet(perm_key_block_1.get(0,27));
		BitSet key_right=des.leftShiftBitSet(perm_key_block_1.get(28,55));
		
		
		
		
		if(perm_key_block_1.get(27)==true)
		 key_left.set(0);
		else
		{
			if(key_left.get(0)==true)
				key_left.flip(0);				
		}
		
		if(perm_key_block_1.get(55)==true)
			 key_right.set(0);
		else
		{
			if(key_right.get(0)==true)
				key_right.flip(0);				
		}
		
		/*
		 * Now we have two sets of left shifted bits
		 * key_left and key_right 
		 */
		
		
		/*
		 * Also we need a combined bitset of key_left and key_right to perform PC2 
		 * Following is the code for initializing bitset with key_left and key_right values
		 * 
		 */
		
		BitSet after_shift= new BitSet(56);
		
		for(int i=0;i<28;i++)
		{
			if(key_left.get(i))
			{
				after_shift.set(i);
				System.out.print(1);
			
			}
			else
			System.out.print(0);
		}
		System.out.print(" ");
	
		for(int i=0;i<28;i++)
		{
			if(key_right.get(i))
			{
				after_shift.set(i+28);
				System.out.print(1);
			}
			else
			System.out.print(0);
		}
		
		System.out.println("");
	
		for(int i=0;i<56;i++)
		{
			if(i==28)
				System.out.print(" ");
			if(after_shift.get(i))
			{
				System.out.print(1);
			}
			else
			System.out.print(0);
		}
		
		System.out.println("");
		
		/*
		 * Now we have a bitset with values after left shifts (56 bits)
		 * Now we can perform Permutation choice 2 on this bitset
		 * 
		 * 
		 */
		
		
		
		/*
		 * Permuted choice Two for 64 bit key
		 * Returns 64 bit but use only first 48 bits.
		 * Perm_key_block_2 comes after PC2
		 */
		BitSet Perm_key_block_2=des_object.permutation(after_shift,des_object.perm_choice_two,48);
		for(int i=0;i<48;i++)
		{
			if(i==24)
				System.out.print(" ");
			if(Perm_key_block_2.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		
		System.out.println("");
		
		
			
		/*
		 * Input expand_data_right and Perm_key_block_2
		 *  
		 * xor these two input bitsets 
		 * Returns 48 bits
		 *
		 */
		BitSet xor_data_key=des_object.xor(expand_data_right,Perm_key_block_2);
		for(int i=0;i<48;i++)
		{
			if(xor_data_key.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		System.out.println("");
		
		
		
		/*
		 * 
		 * initialize a bitset after substitution from S boxes
		 * 
		 */
		
		BitSet after_s_box=new BitSet(32);
		
		// For s1 box
		after_s_box=s_box_work(0,5,xor_data_key,after_s_box,0,0);
		
		
		// For s2 box
		after_s_box=s_box_work(6,11,xor_data_key,after_s_box,1,4);
				
		
		// For s3 box
		after_s_box=s_box_work(12,17,xor_data_key,after_s_box,2,8);
						

		// For s4 box
		after_s_box=s_box_work(18,23,xor_data_key,after_s_box,3,12);
				
		// For s5 box
		after_s_box=s_box_work(24,29,xor_data_key,after_s_box,4,16);
						
		// For s6 box
		after_s_box=s_box_work(30,35,xor_data_key,after_s_box,5,20);
		
		// For s7 box
		after_s_box=s_box_work(36,41,xor_data_key,after_s_box,6,24);
				
		// For s8 box
		after_s_box=s_box_work(42,47,xor_data_key,after_s_box,7,28);
						
		
		/* 
		 * Now we have bitset after substitution applied (sboxes)
		 * named as after_s_box : 32 bit
		 */
		System.out.println();
		
		
		for(int i=0;i<32;i++)
		{
			if(after_s_box.get(i))
			System.out.print(1);
			else
			System.out.print(0);
		}
		System.out.println();
		
		
		/*
		 * Now Do permutation on after_s_box bitset
		 * returns Perm_block
		 */
		
		BitSet Perm_block=des_object.permutation(after_s_box,des_object.Perm,32);
		
		/*
		 * After Permutation P ,do XOR of perm_data_block_left and Perm_block
		 */
		
		
		BitSet xor_leftdata_keyperm=des_object.xor(perm_data_block,Perm_block);
		
		for(int i=0;i<32;i++)
		{
			if(xor_leftdata_keyperm.get(i))
			{
				
				System.out.print(1);
			}
			else
			System.out.print(0);
		}
		
		
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		
		/*
		 * 
		 * Assigning perm_data_block_left and right the appropriate values 
		 * 
		 *
		 */
		perm_data_block_left=(BitSet)perm_data_block_right.clone();
		perm_data_block_right=(BitSet)xor_leftdata_keyperm.clone();
		
		System.out.println();
		
		
		String perm_left_str="";
		
		for(int i=0;i<32;i++)
		{
			if(perm_data_block_left.get(i))
			{
				perm_left_str+="1";
				System.out.print("1");
			}
			else
			{
				perm_left_str+="0";
				System.out.print("0");
			}
		}
		
		System.out.println();
		System.out.println();
		System.out.println(Long.parseLong(perm_left_str,2));
		
		
		String perm_right_str="";
		
		for(int i=0;i<32;i++)
		{
			if(perm_data_block_right.get(i))
			{
				perm_right_str+="1";
				System.out.print("1");
			}
			else
			{
				perm_right_str+="0";
				System.out.print("0");
			}
		}
		
		System.out.println();
		
		
		System.out.println(Long.parseLong(perm_right_str,2));
		System.out.println();
		
		
		
		}// End of 16 rounds
		
		
		// Create  BitSet with Swapped first 32(perm_right) and second 32 bits (perm_left)
		
		BitSet swap_block=new BitSet(64);
		
		
		
		
		String swap_block_str="";
		
		for(int i=0;i<32;i++)
		{
			if(perm_data_block_right.get(i))
			{
				swap_block.set(i);
				System.out.print(1);
				swap_block_str+="1";
			}
			else
			{
			System.out.print(0);
			swap_block_str+="0";
			}
		}
		
		for(int i=32;i<64;i++)
		{
			if(perm_data_block_left.get(i-32))
			{
				swap_block_str+="1";
				swap_block.set(i);
				System.out.print(1);
			}
			else
			{
			System.out.print(0);
			swap_block_str+="0";
			}
		}
		System.out.println();
		
		
		System.out.println();
		System.out.println(Long.parseLong(swap_block_str,2));
		System.out.println();
		
		// Apply inverse permutation to swap bitset
		
		BitSet final_ciphertext=des_object.permutation(swap_block,des_object.inverse_init_perm,64);
		
		System.out.println("CipherText :");
		
		for(int i=0;i<64;i++)
		{
			
			if(final_ciphertext.get(i))
				System.out.print("1");
			else
				System.out.print("0");
		}
	
	}
}