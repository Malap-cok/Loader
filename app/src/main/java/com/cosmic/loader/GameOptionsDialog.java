package com.cosmic.loader;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import android.util.Log;

public class GameOptionsDialog extends Dialog {

    private String gameName;
    private Context context;
    private static final String TAG = "GameOptionsDialog";

    public GameOptionsDialog(Context context, String gameName) {
        super(context);
        this.context = context;
        this.gameName = gameName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_game_options);

        Button btnInstall = findViewById(R.id.btnInstall);
        Button btnLaunch = findViewById(R.id.btnLaunch);
        Button btnUninstall = findViewById(R.id.btnUninstall);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnInstall.setOnClickListener(v -> installGame());
        btnLaunch.setOnClickListener(v -> launchGame());
        btnUninstall.setOnClickListener(v -> uninstallGame());
        btnCancel.setOnClickListener(v -> dismiss());
    }

    private void installGame() {
        try {
            if (!GameManager.hasOBB(gameName)) {
                Toast.makeText(context, 
                    "OBB tidak ada!\nSilahkan install game di device terlebih dahulu.", 
                    Toast.LENGTH_LONG).show();
                dismiss();
                return;
            }

            // Show progress dialog
            AlertDialog progressDialog = new AlertDialog.Builder(context)
                .setTitle("Installing " + gameName)
                .setMessage("Mengkloning game... Mohon tunggu")
                .setCancelable(false)
                .create();
            progressDialog.show();

            // Install in background thread
            new Thread(() -> {
                try {
                    GameManager.installGame(context, gameName);
                    
                    // Get size
                    long size = GameManager.getGameSize(gameName);
                    String sizeStr = formatSize(size);
                    
                    progressDialog.dismiss();
                    
                    Toast.makeText(context, 
                        "✓ Game " + gameName + " berhasil diinstall\nUkuran: " + sizeStr, 
                        Toast.LENGTH_LONG).show();
                    
                    Log.i(TAG, "Game installed: " + gameName + " (" + sizeStr + ")");
                } catch (Exception e) {
                    progressDialog.dismiss();
                    Toast.makeText(context, 
                        "✗ Error: " + e.getMessage(), 
                        Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Installation failed: " + e.getMessage());
                }
                dismiss();
            }).start();

        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Install error: " + e.getMessage());
            dismiss();
        }
    }

    private void launchGame() {
        try {
            if (!GameManager.isGameInstalled(context, gameName)) {
                Toast.makeText(context, 
                    "Game belum diinstall.\nLakukan instalasi terlebih dahulu.", 
                    Toast.LENGTH_LONG).show();
                dismiss();
                return;
            }

            Toast.makeText(context, 
                "Meluncurkan " + gameName + "...", 
                Toast.LENGTH_SHORT).show();
            
            GameManager.launchGame(context, gameName);
            Log.i(TAG, "Game launched: " + gameName);
            
        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Launch error: " + e.getMessage());
        }
        dismiss();
    }

    private void uninstallGame() {
        try {
            if (!GameManager.isGameInstalled(context, gameName)) {
                Toast.makeText(context, 
                    "Game tidak terinstall di clone.", 
                    Toast.LENGTH_SHORT).show();
                dismiss();
                return;
            }

            // Confirm uninstall
            new AlertDialog.Builder(context)
                .setTitle("Uninstall " + gameName)
                .setMessage("Apakah Anda yakin ingin menghapus game ini dari clone?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    GameManager.uninstallGame(context, gameName);
                    Toast.makeText(context, 
                        "✓ Game " + gameName + " berhasil dihapus", 
                        Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Game uninstalled: " + gameName);
                    dismiss();
                })
                .setNegativeButton("Batal", null)
                .show();

        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Uninstall error: " + e.getMessage());
            dismiss();
        }
    }

    /**
     * Format file size to readable string
     */
    private String formatSize(long size) {
        if (size <= 0) return "0 B";
        
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        
        return String.format("%.2f %s", 
            size / Math.pow(1024, digitGroups), 
            units[digitGroups]);
    }
}
