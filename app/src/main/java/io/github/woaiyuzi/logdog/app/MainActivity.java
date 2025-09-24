
package io.github.woaiyuzi.logdog.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import io.github.woaiyuzi.logdog.api.Logger;
import io.github.woaiyuzi.logdog.factory.LoggerFactory;
import io.github.woaiyuzi.logdog.app.databinding.ActivityMainBinding;
import io.github.woaiyuzi.task.TaskLogTest;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private static final Logger log = LoggerFactory.getLogger("MainActivity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate and get instance of binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // set content view to binding's root
        setContentView(binding.getRoot());
        
        log.info("Logger name is {}", log.getName());
        log.trace("Trace message");
        log.debug("Debug message");
        log.info("Info message");
        log.warn("Warn message");
        log.error("Error message");
        
        TaskLogTest.log();
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.binding = null;
    }
}
