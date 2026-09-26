package taput.htdigital;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String POSKO_HP = "+6285296150410";
    private static final String POSKO_KANTOR = "063321113";

    private TextView statusText;
    private TextView tugasText;
    private TextView notifikasiText;

    private String statusPersonel = "SIAP";
    private String reguPersonel = "Regu 1 - Pos Damkar Tarutung";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buatTampilan();
    }

    private void buatTampilan() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(14, 10, 14, 20);

        // =========================
        // HEADER
        // =========================

        TextView header = new TextView(this);
        header.setText("DAMKAR TAPUT HT DIGITAL");
        header.setTextSize(24);
        header.setTextColor(0xFFFFFFFF);
        header.setGravity(Gravity.CENTER);
        header.setPadding(10, 18, 10, 18);
        header.setBackgroundColor(0xFFC91D25);

        root.addView(header);

        // =========================
        // STATUS
        // =========================

        statusText = new TextView(this);
        statusText.setText("🟢 Status personel: " + statusPersonel);
        statusText.setTextSize(22);
        statusText.setPadding(8, 12, 8, 8);

        root.addView(statusText);

        // =========================
        // REGU
        // =========================

        TextView reguText = new TextView(this);
        reguText.setText("👥 Regu: " + reguPersonel);
        reguText.setTextSize(21);
        reguText.setPadding(8, 8, 8, 8);

        root.addView(reguText);

        // =========================
        // TUGAS
        // =========================

        TextView tugasLabel = new TextView(this);
        tugasLabel.setText("📢 Tugas Aktif");
        tugasLabel.setTextSize(21);
        tugasLabel.setPadding(8, 8, 8, 2);

        root.addView(tugasLabel);

        tugasText = new TextView(this);
        tugasText.setText("Belum ada tugas aktif");
        tugasText.setTextSize(19);
        tugasText.setPadding(8, 0, 8, 8);

        root.addView(tugasText);

        // =========================
        // NOTIFIKASI
        // =========================

        TextView notifikasiLabel = new TextView(this);
        notifikasiLabel.setText("🔔 Notifikasi");
        notifikasiLabel.setTextSize(21);
        notifikasiLabel.setPadding(8, 8, 8, 2);

        root.addView(notifikasiLabel);

        notifikasiText = new TextView(this);
        notifikasiText.setText("Status diperbarui: SIAP");
        notifikasiText.setTextSize(19);
        notifikasiText.setPadding(8, 0, 8, 10);

        root.addView(notifikasiText);

        // =========================
        // LAPOR CEPAT
        // =========================

        Button laporButton = tombol("🚨  LAPOR CEPAT KE POSKO");

        laporButton.setOnClickListener(v -> {
            telepon(POSKO_HP);
        });

        root.addView(laporButton);

        // =========================
        // HUBUNGI POSKO
        // =========================

        Button hubungiButton = tombol("📞  HUBUNGI POSKO");

        hubungiButton.setOnClickListener(v -> tampilkanKontakPosko());

        root.addView(hubungiButton);

        // =========================
        // STATUS PERSONEL
        // =========================

        Button statusButton = tombol("🟢  STATUS PERSONEL");

        statusButton.setOnClickListener(v -> tampilkanStatus());

        root.addView(statusButton);

        // =========================
        // REGU & PERSONEL
        // =========================

        Button reguButton = tombol("👥  REGU & PERSONEL");

        reguButton.setOnClickListener(v -> tampilkanRegu());

        root.addView(reguButton);

        // =========================
        // HT DIGITAL / PTT
        // =========================

        Button pttButton = tombol("📻  HT DIGITAL / PTT");

        pttButton.setOnClickListener(v -> tampilkanPTT());

        root.addView(pttButton);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(root);

        setContentView(scrollView);
    }

    // =====================================================
    // TOMBOL
    // =====================================================

    private Button tombol(String teks) {

        Button button = new Button(this);

        button.setText(teks);
        button.setTextSize(18);
        button.setAllCaps(false);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 7, 0, 7);

        button.setLayoutParams(params);

        return button;
    }

    // =====================================================
    // TELEPON POSKO
    // =====================================================

    private void telepon(String nomor) {

        try {

            Intent intent = new Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:" + nomor)
            );

            startActivity(intent);

        } catch (Exception e) {

            Toast.makeText(
                    this,
                    "Tidak dapat membuka telepon",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    // =====================================================
    // KONTAK POSKO
    // =====================================================

    private void tampilkanKontakPosko() {

        String[] pilihan = {
                "📞 Posko 0633 21113",
                "📱 Posko +62 852-9615-0410"
        };

        new AlertDialog.Builder(this)
                .setTitle("HUBUNGI POSKO")
                .setItems(pilihan, (dialog, which) -> {

                    if (which == 0) {
                        telepon(POSKO_KANTOR);
                    } else {
                        telepon(POSKO_HP);
                    }

                })
                .setNegativeButton("Tutup", null)
                .show();
    }

    // =====================================================
    // STATUS PERSONEL
    // =====================================================

    private void tampilkanStatus() {

        String[] status = {
                "🟢 SIAP",
                "🟡 OTW",
                "🔵 TIBA",
                "🟠 OPERASI",
                "⚪ SELESAI"
        };

        new AlertDialog.Builder(this)
                .setTitle("STATUS PERSONEL")
                .setItems(status, (dialog, which) -> {

                    switch (which) {

                        case 0:
                            statusPersonel = "SIAP";
                            break;

                        case 1:
                            statusPersonel = "OTW";
                            break;

                        case 2:
                            statusPersonel = "TIBA";
                            break;

                        case 3:
                            statusPersonel = "OPERASI";
                            break;

                        case 4:
                            statusPersonel = "SELESAI";
                            break;
                    }

                    updateStatus();

                })
                .setNegativeButton("Tutup", null)
                .show();
    }

    private void updateStatus() {

        String tanda = "🟢";

        if (statusPersonel.equals("OTW")) {
            tanda = "🟡";
        } else if (statusPersonel.equals("TIBA")) {
            tanda = "🔵";
        } else if (statusPersonel.equals("OPERASI")) {
            tanda = "🟠";
        } else if (statusPersonel.equals("SELESAI")) {
            tanda = "⚪";
        }

        statusText.setText(
                tanda + " Status personel: " + statusPersonel
        );

        notifikasiText.setText(
                "Status diperbarui: " + statusPersonel
        );

        Toast.makeText(
                this,
                "Status: " + statusPersonel,
                Toast.LENGTH_SHORT
        ).show();
    }

    // =====================================================
    // REGU & PERSONEL
    // =====================================================

    private void tampilkanRegu() {

        String[] pos = {

                "🚒 POS DAMKAR TARUTUNG\n" +
                "Regu 1\n" +
                "Regu 2\n" +
                "Regu 3",

                "🚒 POS DAMKAR SIBORONG-BORONG\n" +
                "Regu 1\n" +
                "Regu 2\n" +
                "Regu 3",

                "🚒 POS DAMKAR PAHAE\n" +
                "Regu 1\n" +
                "Regu 2\n" +
                "Regu 3",

                "🚒 POS DAMKAR PANGARIBUAN\n" +
                "Regu 1\n" +
                "Regu 2\n" +
                "Regu 3"
        };

        new AlertDialog.Builder(this)
                .setTitle("REGU & PERSONEL")
                .setItems(pos, (dialog, which) -> {

                    if (which == 0) {
                        pilihRegu("Tarutung");
                    } else if (which == 1) {
                        pilihRegu("Siborong-borong");
                    } else if (which == 2) {
                        pilihRegu("Pahae");
                    } else {
                        pilihRegu("Pangaribuan");
                    }

                })
                .setNegativeButton("Tutup", null)
                .show();
    }

    private void pilihRegu(String namaPos) {

        String[] regu = {
                "Regu 1",
                "Regu 2",
                "Regu 3"
        };

        new AlertDialog.Builder(this)
                .setTitle("POS DAMKAR " + namaPos.toUpperCase())
                .setItems(regu, (dialog, which) -> {

                    reguPersonel =
                            regu[which] +
                            " - Pos Damkar " +
                            namaPos;

                    Toast.makeText(
                            this,
                            "Dipilih: " + reguPersonel,
                            Toast.LENGTH_SHORT
                    ).show();

                })
                .setNegativeButton("Kembali", null)
                .show();
    }

    // =====================================================
    // HT DIGITAL / PTT
    // =====================================================

    private void tampilkanPTT() {

        String[] pilihan = {

                "📡 Status koneksi",

                "🎙️ Tekan & Bicara",

                "👥 Pilih Regu Komunikasi",

                "📢 Komunikasi Seluruh Personel"
        };

        new AlertDialog.Builder(this)
                .setTitle("HT DIGITAL / PTT")
                .setItems(pilihan, (dialog, which) -> {

                    if (which == 0) {

                        new AlertDialog.Builder(this)
                                .setTitle("STATUS HT DIGITAL")
                                .setMessage(
                                        "Status: BELUM TERHUBUNG\n\n" +
                                        "PTT antarperangkat membutuhkan " +
                                        "server komunikasi internet."
                                )
                                .setPositiveButton("OK", null)
                                .show();

                    } else if (which == 1) {

                        new AlertDialog.Builder(this)
                                .setTitle("PTT")
                                .setMessage(
                                        "Fungsi Tekan & Bicara akan " +
                                        "mengirim suara ke personel " +
                                        "yang terhubung pada kanal yang sama."
                                )
                                .setPositiveButton("OK", null)
                                .show();

                    } else if (which == 2) {

                        pilihKanalPTT();

                    } else {

                        new AlertDialog.Builder(this)
                                .setTitle("KOMUNIKASI SELURUH PERSONEL")
                                .setMessage(
                                        "Kanal seluruh personel dipersiapkan " +
                                        "untuk komunikasi HT Digital."
                                )
                                .setPositiveButton("OK", null)
                                .show();
                    }

                })
                .setNegativeButton("Tutup", null)
                .show();
    }

    private void pilihKanalPTT() {

        String[] kanal = {

                "Tarutung - Regu 1",
                "Tarutung - Regu 2",
                "Tarutung - Regu 3",

                "Siborong-borong - Regu 1",
                "Siborong-borong - Regu 2",
                "Siborong-borong - Regu 3",

                "Pahae - Regu 1",
                "Pahae - Regu 2",
                "Pahae - Regu 3",

                "Pangaribuan - Regu 1",
                "Pangaribuan - Regu 2",
                "Pangaribuan - Regu 3"
        };

        new AlertDialog.Builder(this)
                .setTitle("PILIH KANAL HT")
                .setItems(kanal, (dialog, which) -> {

                    Toast.makeText(
                            this,
                            "Kanal dipilih: " + kanal[which],
                            Toast.LENGTH_SHORT
                    ).show();

                })
                .setNegativeButton("Tutup", null)
                .show();
    }
}