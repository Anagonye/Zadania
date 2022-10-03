public class Finder{
    private boolean search(int[] numbers, int x){
        for(int i = 0; i <= numbers.length-1; i++ ){
            if(x > numbers[i]){
                return false;
            }
            else if( x == numbers[i]) {
                return true;
            }

        }
        return false;
    }


    //Złożoność obliczeniowa tej metody wynosi <= N;

}
