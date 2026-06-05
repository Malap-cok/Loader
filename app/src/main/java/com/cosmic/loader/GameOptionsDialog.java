package com.cosmic.loader;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class GameOptionsDialog extends Dialog {

    private String gameName;
    private Context context;

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
        if (GameManager.hasOBB(gameName)) {
            GameManager.installGame(context, gameName);
            Toast.makeText(context, "Game " + gameName + " berhasil diinstall", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "OBB tidak ada, silahkan install game di device terlebih dahulu.", Toast.LENGTH_LONG).show();
        }
        dismiss();
    }

    private void launchGame() {
        if (GameManager.isGameInstalled(context, gameName)) {
            GameManager.launchGame(context, gameName);
            Toast.makeText(context, "Meluncurkan " + gameName, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Game belum diinstall. Lakukan instalasi terlebih dahulu.", Toast.LENGTH_SHORT).show();
        }
        dismiss();
    }

    private void uninstallGame() {
        GameManager.uninstallGame(context, gameName);
        Toast.makeText(context, "Game " + gameName + " berhasil dihapus", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
