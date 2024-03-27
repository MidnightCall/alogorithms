package com.kojikoji.java;

import org.junit.Test;

import javax.swing.*;
import java.net.PasswordAuthentication;

/**
 * @ClassName TrieTree
 * @Description
 * @Author kojikoji 1310402980@qq.com
 * @Date 2022/12/29 13:25
 * @Version
 */

public class TrieTree {
    public class TrieNode{
        public int pass;
        public int end;
        public TrieNode[] nexts;
        public TrieNode(){
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public class Trie{
        private TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        public void insert(String word){
            if(word == null){
                return;
            }

            int index = 0;
            TrieNode node = root;
            char[] chs = word.toCharArray();
            for(int i = 0; i < word.length(); ++i){
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
                ++node.pass;
            }
            ++node.end;
        }

        public void delete(String word){
            if(search(word) != 0){
                int index = 0;
                TrieNode node = root;
                char[] chs = word.toCharArray();
                for(int i = 0; i < chs.length; ++i){
                    index = chs[i] - 'a';
                    if(--node.nexts[index].pass == 0){
                        node.nexts[index] = null;
                    }
                    node = node.nexts[index];
                }
                --node.end;
            }
        }

        public int search(String word){
            if(word == null){
                return 0;
            }
            int index = 0;
            char[] chs = word.toCharArray();
            TrieNode node = root;
            for(int i = 0; i < chs.length; ++i){
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int prefixNum(String prefix){
            if(prefix == null){
                return 0;
            }

            int index = 0;
            char[] chs = prefix.toCharArray();
            TrieNode node = root;
            for(int i = 0; i < chs.length; ++i){
                index = chs[i] - 'a';
                if(node.nexts[index] == null){
                    return 0;
                }
                node = node.nexts[index];
            }

            return node.pass;
        }
    }

    @Test
    public void test(){
        String[] words = {"Hello", "error", "nimeo"};
        System.out.println(String.join(" ", words));
    }

}
