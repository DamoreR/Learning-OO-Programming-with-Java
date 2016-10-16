/**
 * BigInt is a doubly linked list that holds each digit of a
 * big integer. Private class IntNode is used for each node.
 * 
 * @author: Robert Damore
 * @version: Winter 2016
 */

public class BigInt implements Comparable<BigInt>{

	IntNode start;
	char sign;

	/**
	 * Construct BigInt with String
	 * @param val String input
	 * @throws BigIntFormatException non integer input
	 */
	public BigInt(String val) throws BigIntFormatException{

		String value = val.trim();
		if(value.isEmpty()||value.contains("\n")||value.contains("\t"))
			throw new BigIntFormatException();
		if(stringIsSigned(value)){
			sign = value.charAt(0);
			value = value.substring(1);
			value = value.trim(); //just in case of space after sign
		}else
			sign = '+';
		for(int i=0;i<value.length();i++){
			if(!Character.isDigit(value.charAt(i))){
				throw new BigIntFormatException();
			}
		}
		start = new IntNode(Integer.parseInt(value.substring(0, 1)));
		IntNode current = start;
		for(int i=1;i<value.length();i++){
			current.next = new IntNode(Integer.parseInt(value.substring(i, i+1)), current);
			current = current.next;
		}
	}
	/**
	 * Deep copy constructor
	 * @param val BigInt input for copy
	 */
	public BigInt(BigInt val){
		sign = val.sign;
		start = new IntNode(val.start.data);
		IntNode current = val.start;
		IntNode copy = new IntNode(current.next.data);
		start.next = copy;
		copy.last = start;
		current = current.next;
		while(current.next!=null){
			copy.next = new IntNode(current.next.data, copy);
			copy = copy.next;
			current = current.next;
		}
	}
	/**
	 * Construct BigInt with long value
	 * @param val long value input
	 */
	public BigInt(long val){
		if(val < 0)
			sign = '-';
		else
			sign = '+';
		val = Math.abs(val);
		IntNode current = new IntNode((int)(val%10l));
		val /= 10l;
		while(val!=0l){
			current.last = new IntNode((int)(val%10l), null, current);
			current = current.last;
			val /= 10l;
		}
		start = current;
	}
	/**
	 * Adds two BigInts and returns a BigInt of the sum
	 * @param val BigInt input to be added to this
	 * @return BigInt sum
	 */
	public BigInt add(BigInt val){
		BigInt result = new BigInt(0);
		if(sign==val.sign){
			IntNode currentThis = start;
			IntNode currentVal = val.start;
			while(currentThis.next!=null)
				currentThis = currentThis.next;
			while(currentVal.next!=null)
				currentVal = currentVal.next;
			int sum = 0;
			int carry = 0;
			sum = currentThis.data + currentVal.data;
			result.start.data = sum%10;
			carry = sum/10;
			while(currentThis.last!=null&&currentVal.last!=null){
				sum = currentThis.last.data + currentVal.last.data + carry;
				result.start = new IntNode(sum%10, null, result.start);
				carry = sum/10;
				currentThis = currentThis.last;
				currentVal = currentVal.last;
			}
			if(currentThis.last==null&&currentVal.last!=null){
				while(currentVal.last!=null){
					sum = currentVal.last.data + carry;
					result.start = new IntNode(sum%10, null, result.start);
					carry = sum/10;
					currentVal = currentVal.last;
				}
				if(carry>0)
					result.start = new IntNode(carry, null, result.start);
			}else if(currentThis.last!=null&&currentVal.last==null){
				while(currentThis.last!=null){
					sum = currentThis.last.data + carry;
					result.start = new IntNode(sum%10, null, result.start);
					carry = sum/10;
					currentThis = currentThis.last;
				}
				if(carry>0)
					result.start = new IntNode(carry, null, result.start);
			}else{
				if(carry>0)
					result.start = new IntNode(carry, null, result.start);
			}
			result.sign = sign;
		}else{
			//positive plus negative goes here
		}
		return result;
	}

	public int compareTo(BigInt val){	
		if(sign!=val.sign){
			if(sign=='+')
				return 1;
			else 
				return -1;
		}

		IntNode currentNode = start;
		IntNode comparingNode = val.start;

		while(currentNode.next!=null){
			if(comparingNode.next!=null){
				currentNode = currentNode.next;
				comparingNode = comparingNode.next;
			}
			else{
				if(sign=='+')
					return 1;
				else
					return -1;
			}
		}
		if(comparingNode.next!=null)
			if(sign=='+')
				return -1;
			else
				return 1;
		else{
			if(this.equals(val))
				return 0;
			else{
				currentNode = start;
				comparingNode = val.start;

				do{
					if(currentNode.data>comparingNode.data){
						if(sign=='+')
							return 1;
						else
							return -1;
					}
					else if(currentNode.data<comparingNode.data){
						if(sign=='+')
							return -1;
						else
							return 1;
					}
					currentNode = currentNode.next;
					comparingNode = comparingNode.next;
				}while(currentNode!=null);

				return 0;
			}
		}
	}
	public boolean equals(BigInt val){
		if(sign==val.sign){
			IntNode currentNode = start;
			IntNode comparingNode = val.start;

			while(currentNode.next!=null){
				if(comparingNode.next!=null){
					currentNode = currentNode.next;
					comparingNode = comparingNode.next;
				}
				else
					return false;
			}
			if(comparingNode.next!=null)
				return false;
			else{
				currentNode = start;
				comparingNode = val.start;
				while(currentNode.next!=null){
					if(currentNode.data!=comparingNode.data)
						return false;
					currentNode = currentNode.next;
					comparingNode = comparingNode.next;
				}
				if(currentNode.data==comparingNode.data)
					return true;
			}

		}
		return false;
	}
	/**
	 * negates the BigInt
	 * @return negated BigInt input
	 */
	public BigInt negate(){		//Return a BigInt whose value is -this
		BigInt negInt = new BigInt(this);
		if(negInt.sign == '-')
			negInt.sign = '+';
		else
			negInt.sign = '-';
		return negInt;
	}
	public String toString(){	//Return this BigInt as a String. Negative values start with a leading negative sign. Positive values have no leading sign.
		String result = "";
		IntNode current = start;
		if(current!=null){
			if(sign=='-')
				result+=sign;
			result += current.data;
			while(current.next!=null){
				current = current.next;
				result += current.data;
			}
		}
		return result;
	}
	//checks is String input has sign
	private boolean stringIsSigned(String val){
		if(val.startsWith("+")||val.startsWith("-"))
			return true;
		return false;
	}
	//Nodes for linked list
	private class IntNode {
		IntNode last;
		IntNode next;
		int data;
		public IntNode(int x){
			data = x;
			last = null;
			next = null;
		}
		public IntNode(int x, IntNode l){
			data = x;
			last = l;
			next = null;
		}
		public IntNode(int x, IntNode l, IntNode n){
			data = x;
			last = l;
			next = n;
		}
	}
}