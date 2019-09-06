package com.electro2560.dev.forcegrow.updater;

import org.apache.commons.lang.StringUtils;

public class Version implements Comparable<Version> {
	private int[] parts;

	public Version(String value) {
		if (value == null)
			throw new IllegalArgumentException("Version can not be null");
		if (!value.matches("^[0-9]+(\\.[0-9]+)*$"))
			throw new IllegalArgumentException("Invalid version format");
		String[] split = value.split("\\.");
		this.parts = new int[split.length];
		for (int i = 0; i < split.length; i++) this.parts[i] = Integer.parseInt(split[i]);
	}

	public static int compare(Version verA, Version verB) {
		if (verA == verB) return 0;
		if (verA == null) return -1;
		if (verB == null) return 1;
		
		int max = Math.max(verA.parts.length, verB.parts.length);
		
		for (int i = 0; i < max; i++) {
			int partA = i < verA.parts.length ? verA.parts[i] : 0;
			int partB = i < verB.parts.length ? verB.parts[i] : 0;
			if (partA < partB) return -1;
			if (partA > partB) return 1;
		}
		
		return 0;
	}

	public static boolean equals(Version verA, Version verB) {
		return (verA == verB) || ((verA != null) && (verB != null) && (compare(verA, verB) == 0));
	}

	public String toString() {
		String[] split = new String[this.parts.length];
		for (int i = 0; i < this.parts.length; i++) split[i] = String.valueOf(this.parts[i]);
		return StringUtils.join(split, ".");
	}

	public int compareTo(Version that) {
		return compare(this, that);
	}

	public boolean equals(Object that) {
		return (that instanceof Version) && equals(this, (Version) that);
	}
}
