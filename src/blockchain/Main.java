package blockchain;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Block>blockchain = new ArrayList<>();

    public static void main(String []args) throws NoSuchAlgorithmException {
        blockchain.add(new Block("0","hello blockchain"));
        System.out.println(blockchain.get(0).hash);

        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash,"hey,i'm second block!"));
        System.out.println(blockchain.get(1).hash);

        blockchain.add(new Block(blockchain.get(blockchain.size()-1).hash,"third block!",40));
        System.out.println(blockchain.get(2).hash);

        blockchain.get(2).mineBlock();
        System.out.println(isChainValid());

      //Block firstBlock = new Block("0","hello blockchain");
      //Block secondBlock = new Block(firstBlock.previousHash,"hey,i'm second block!");
        // System.out.println(firstBlock.hash);
       //System.out.println(secondBlock.hash);

    }
//metoda sprawdzajaca czy blockchain jest poprawny
    //sprawdzimy czy hash danego bloku będzie taki sam jak previousHash kolejnego bloku
    private static boolean isChainValid() throws NoSuchAlgorithmException {
      Block currentBlock;
      Block previousBlock;

      for (int i = 1; i<blockchain.size(); i++){
          currentBlock = blockchain.get(i);
          previousBlock = blockchain.get(i-1);

          if (!currentBlock.hash.equals(Hash.getHash(currentBlock))){
              System.out.println("curr hash different!");
              return false;
          }
          if (!previousBlock.hash.equals(currentBlock.previousHash)){
              System.out.println("prev hash different");
              return false;
          }
      }
      return true;
    }
}
