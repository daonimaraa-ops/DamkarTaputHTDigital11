package id.damkar.taput.htdigital;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    private TextView statusView;
    private TextView taskView;
    private TextView notificationView;

    private static final String POSKO_NUMBER = "113";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildHome();
    }

    private TextView createText(String text, float size) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextSize(size);
        tv.setTextColor(Color.DKGRAY);
        tv.setPadding(20, 15, 20, 15);
        return tv;
    }

    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setTextSize(16);
        button.setAllCaps(false);
        return button;
    }

    private void buildHome() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(12, 12, 12, 12);

        TextView header =
                createText("DAMKAR TAPUT HT DIGITAL", 21);

        header.setGravity(Gravity.CENTER);
        header.setTextColor(Color.WHITE);
        header.setBackgroundColor(Color.rgb(198, 40, 40));

        root.addView(
                header,
                new LinearLayout.LayoutParams(
                        -1,
                        75
                )
        );

        statusView =
                createText("🟢 Status personel: SIAP", 18);
        root.addView(statusView);

        root.addView(
                createText("👥 Regu: Regu Macan", 18)
        );

        taskView =
                createText(
                        "📢 Tugas Aktif\nBelum ada tugas aktif",
                        17
                );
        root.addView(taskView);

        notificationView =
                createText(
                        "🔔 Notifikasi\nTidak ada notifikasi baru",
                        17
                );
        root.addView(notificationView);

        Button report =
                createButton("🚨 LAPOR CEPAT KE POSKO");

        report.setOnClickListener(
                v -> showReport()
        );

        root.addView(report);

        Button call =
                createButton("📞 HUBUNGI POSKO");

        call.setOnClickListener(
                v -> {
                    Intent intent =
                            new Intent(
                                    Intent.ACTION_DIAL,
                                    Uri.parse(
                                            "tel:" + POSKO_NUMBER
                                    )
                            );
                    startActivity(intent);
                }
        );

        root.addView(call);

        Button status =
                createButton("🟢 STATUS PERSONEL");

        status.setOnClickListener(
                v -> showStatus()
        );

        root.addView(status);

        Button team =
                createButton("👥 REGU & PERSONEL");

        team.setOnClickListener(
                v -> showTeam()
        );

        root.addView(team);

        Button ptt =
                createButton("📻 HT DIGITAL / PTT");

        ptt.setOnClickListener(
                v -> showPTT()
        );

        root.addView(ptt);

        setContentView(root);
    }

    private void showReport() {

        LinearLayout layout =
                new LinearLayout(this);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setPadding(
                25,
                5,
                25,
                5
        );

        Spinner typeSpinner =
                new Spinner(this);

        String[] types = {
                "Kebakaran",
                "Kecelakaan",
                "Penyelamatan",
                "Pohon tumbang",
                "Tawon/serangga",
                "Banjir/bencana",
                "Lainnya"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        types
                );

        typeSpinner.setAdapter(adapter);

        layout.addView(typeSpinner);

        EditText location =
                new EditText(this);

        location.setHint("Lokasi kejadian");

        layout.addView(location);

        EditText description =
                new EditText(this);

        description.setHint(
                "Keterangan kejadian"
        );

        description.setMinLines(3);

        layout.addView(description);

        new AlertDialog.Builder(this)
                .setTitle("🚨 LAPOR CEPAT KE POSKO")
                .setView(layout)
                .setNegativeButton(
                        "BATAL",
                        null
                )
                .setPositiveButton(
                        "KIRIM LAPORAN",
                        (dialog, which) -> {

                            String time =
                                    new SimpleDateFormat(
                                            "dd/MM/yyyy HH:mm",
                                            Locale.getDefault()
                                    ).format(
                                            new Date()
                                    );

                            String type =
                                    typeSpinner
                                            .getSelectedItem()
                                            .toString();

                            String loc =
                                    location
                                            .getText()
                                            .toString();

                            String desc =
                                    description
                                            .getText()
                                            .toString();

                            taskView.setText(
                                    "📢 Tugas Aktif\n"
                                    + type
                                    + "\n📍 "
                                    + loc
                                    + "\n📝 "
                                    + desc
                                    + "\n⏰ "
                                    + time
                            );

                            notificationView.setText(
                                    "🔔 Notifikasi\n"
                                    + "Laporan berhasil dikirim ke Posko"
                            );

                            Toast.makeText(
                                    MainActivity.this,
                                    "Laporan berhasil dikirim",
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                )
                .show();
    }

    private void showStatus() {

        String[] statuses = {
                "SIAP",
                "OTW",
                "TIBA",
                "OPERASI",
                "SELESAI"
        };

        new AlertDialog.Builder(this)
                .setTitle("STATUS PERSONEL")
                .setItems(
                        statuses,
                        (dialog, position) -> {

                            String selected =
                                    statuses[position];

                            statusView.setText(
                                    "🟢 Status personel: "
                                    + selected
                            );

                            notificationView.setText(
                                    "🔔 Notifikasi\n"
                                    + "Status diperbarui: "
                                    + selected
                            );
                        }
                )
                .show();
    }

    private void showTeam() {

        new AlertDialog.Builder(this)
                .setTitle("👥 REGU MACAN")
                .setMessage(
                        "DAFTAR PERSONEL\n\n"
                        + "01. Personel 01 — SIAP\n"
                        + "02. Personel 02 — SIAP\n"
                        + "03. Personel 03 — SIAP\n"
                        + "04. Personel 04 — SIAP\n"
                        + "05. Personel 05 — SIAP\n\n"
                        + "Data personel nantinya "
                        + "dapat dihubungkan ke Posko."
                )
                .setPositiveButton(
                        "TUTUP",
                        null
                )
                .show();
    }

    private void showPTT() {

        new AlertDialog.Builder(this)
                .setTitle("📻 HT DIGITAL / PTT")
                .setMessage(
                        "SALURAN: REGU MACAN\n\n"
                        + "HT DIGITAL AKTIF\n\n"
                        + "Tekan tombol PTT untuk "
                        + "komunikasi suara.\n\n"
                        + "Komunikasi realtime antar "
                        + "perangkat akan membutuhkan "
                        + "server/backend."
                )
                .setPositiveButton(
                        "TUTUP",
                        null
                )
                .show();
    }
}