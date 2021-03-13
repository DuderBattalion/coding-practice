package com.leetcode.util;

public class UnionFind {
    int[] members;

    public UnionFind(int n) {
        members = new int[n];
        for (int i = 0; i < n; i++) {
            members[i] = i;
        }
    }

    public boolean find(int a, int b) {
        return members[a] == members[b];
    }

    public void union(int a, int b) {
        int oldMembership = members[a];
        int newMembersship = members[b];

        for (int i = 0; i < members.length; i++) {
            if (members[i] == oldMembership) {
                members[i] = newMembersship;
            }
        }
    }
}
