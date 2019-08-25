package com.jm.assignment;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents method to check if given list of string represents IPv4
 * address, IPv6 address or neither.<br>
 * The typical format for an IPv4 address is A.B.C.D where A, B, C, and Dare
 * integers in the inclusive range between 0 and 255.<br>
 * IPv6 addresses are represented by eight colon-separated sixteen-bit groups,
 * where each sixteen-bit group is written using 1 to 4 hexadecimal digits.
 * 
 * @author Kajal
 *
 */
public class IPAddressCheck {

	private final static String DOT_SEPARATOR = ".";
	/**
	 * IPv6 regex
	 */
	private final static String IPV6_REGEX = "([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}";

	public static void main(String[] args) {

		Scanner src = new Scanner(System.in);
		System.out.println("Enter number of inputs");
		int n = src.nextInt();
		String ipArray[] = new String[n];
		System.out.println("Enter input strings");
		src.nextLine();
		for (int i = 0; i < n; i++) {
			ipArray[i] = src.nextLine();
		}
		src.close();

		IPAddressCheck test = new IPAddressCheck();
		String output[] = test.checkIPs(ipArray);
		for (int op = 0; op < output.length; op++) {
			System.out.println(output[op]);
		}
	}

	/**
	 * This method takes string array as input to check if string is IPv4, IPv6
	 * or neither. It adds the respective identified value in string array to
	 * return required output.
	 * 
	 * @param ipArray
	 * @return
	 */
	private String[] checkIPs(String[] ipArray) {

		String output[] = new String[ipArray.length];
		for (int i = 0; i < ipArray.length; i++) {
			if (isIPv4(ipArray[i])) {
				output[i] = "IPv4";
			} else if (isIPv6(ipArray[i])) {
				output[i] = "IPv6";
			} else {
				output[i] = "Neither";
			}
		}
		return output;
	}

	/**
	 * This method is used to check whether given string is valid IPv4
	 * address.<br>
	 * It splits the string with dot and checks if there are exactly 4 segments
	 * as required and whether every segment is satisfying 0-255 criteria of
	 * IPv4
	 * 
	 * @param ip
	 * @return
	 */
	private boolean isIPv4(String ip) {

		String[] groups = ip.split(Pattern.quote(DOT_SEPARATOR));
		if (groups.length != 4)
			return false;
		else {
			for (int j = 0; j < groups.length; j++) {
				try {
					int a = Integer.parseInt(groups[j]);
					if (!(a >= 0 && a < 256)) {
						return false;
					}
				} catch (NumberFormatException e) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This method is used to check if given string is valid Ipv6 string.<br>
	 * It matches the string with IPv6 regex pattern. If match is found then
	 * returns true else false.
	 * 
	 * @param ip
	 * @return
	 */
	private boolean isIPv6(String ip) {

		Pattern pattern = Pattern.compile(IPV6_REGEX);

		Matcher matcher = pattern.matcher(ip);
		if (matcher.matches())
			return true;
		else
			return false;

	}

}