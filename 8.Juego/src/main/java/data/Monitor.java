package data;

public class Monitor {

    boolean bool_bloquear=false;

    public Monitor( ) {

    }

    public synchronized void bloquear(){

        while(bool_bloquear){
            try {
                //System.out.println(Thread.currentThread().getName() + " Bloqueado");
                wait();
            } catch (InterruptedException e) {
                System.out.println("Error"+e.getMessage());
            }
        }


    }

    public synchronized void desbloquear(){
        bool_bloquear=false;
        notifyAll();
    }

    public void setBloquear(){
        this.bool_bloquear=true;
    }

}
