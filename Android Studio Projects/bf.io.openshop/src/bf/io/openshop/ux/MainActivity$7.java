package bf.io.openshop.ux;

class MainActivity$7 implements Runnable {
    @Override
    public void run() {
        if (MainActivity.access$100(MainActivity.this) != 0 && MainActivity.access$100(MainActivity.this) != -131) {
            MainActivity.access$200(MainActivity.this).setText((CharSequence)MainActivity.this.getString(2131230936, new Object[] { MainActivity.access$100(MainActivity.this) }));
            MainActivity.access$200(MainActivity.this).setVisibility(0);
            return;
        }
        MainActivity.access$200(MainActivity.this).setVisibility(8);
    }
}