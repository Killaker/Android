package bf.io.openshop.ux;

import bf.io.openshop.interfaces.*;
import bf.io.openshop.entities.*;

class MainActivity$12 implements LoginDialogInterface {
    @Override
    public void successfulLoginOrRegistration(final User user) {
        MainActivity.this.onCartSelected();
    }
}