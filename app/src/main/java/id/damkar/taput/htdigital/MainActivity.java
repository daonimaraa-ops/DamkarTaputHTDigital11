package id.damkar.taput.htdigital;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // ==============================
    // KONTAK POSKO
    // ==============================

    private static final String POSKO_HP = "+6285296150410";
    private static final String POSKO_KANTOR = "063321113";

    // ==============================
    // DATA PERSONEL
    // ==============================

    private String statusPersonel = "SIAP";
    private String reguPersonel = "Regu 1 - Pos Damkar Tarutung";

    private TextView statusText;
    private TextView reguText;
    private TextView notifikasiText;
    private TextView tugasText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buatTampilanUtama();
    }

    // ============================================================
    // TAMPILAN UTAMA
    // ============================================================

    private void buatTampilanUtama() {

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(14, 8, 14, 20);
        root.setBackgroundColor(Color.WHITE);

        // ========================================================
        // HEADER
        // ========================================================

        TextView header = new TextView(this);

        header.setText("DAMKAR TAPUT HT DIGITAL");
        header.setTextSize(24);
        header.setTextColor(Color.WHITE);
        header.setGravity(Gravity.CENTER);
        header.setPadding(8, 16, 8, 16);
        header.setBackgroundColor(Color.rgb(198, 28, 36));

        root.addView(header);

        // ========================================================
        // STATUS PERSONEL
        // ========================================================

        statusText = new TextView(this);

        statusText.setText("🟢 Status personel: SIAP");
        statusText.setTextSize(21);
        statusText.setTextColor(Color.DKGRAY);
        statusText.setPadding(8, 12, 8, 8);

        root.addView(statusText);

        // ========================================================
        // REGU
        // ========================================================

        reguText = new TextView(this);

        reguText.setText("👥 Regu: " + reguPersonel);
        reguText.setTextSize(20);
        reguText.setTextColor(Color.DKGRAY);
        reguText.setPadding(8, 8, 8, 8);

        root.addView(reguText);

        // ========================================================
        // TUGAS AKTIF
        // ========================================================

        TextView tugasLabel = new TextView(this);

        tugasLabel.setText("📢 Tugas Aktif");
        tugasLabel.setTextSize(21);
        tugasLabel.setTextColor(Color.DKGRAY);
        tugasLabel.setPadding(8, 8, 8, 2);

        root.addView(tugasLabel);

        tugasText = new TextView(this);

        tugasText.setText("Belum ada tugas aktif");
        tugasText.setTextSize(18);
        tugasText.setTextColor(Color.DKGRAY);
        tugasText.setPadding(8, 0, 8, 8);

        root.addView(tugasText);

        // ========================================================
        // NOTIFIKASI
        // ========================================================

        TextView notifikasiLabel = new TextView(this);

        notifikasiLabel.setText("🔔 Notifikasi");
        notifikasiLabel.setTextSize(21);
        notifikasiLabel.setTextColor(Color.DKGRAY);
        notifikasiLabel.setPadding(8, 8, 8, 2);

        root.addView(notifikasiLabel);

        notifikasiText = new TextView(this);

        notifikasiText.setText("Status diperbarui: SIAP");
        notifikasiText.setTextSize(18);
        notifikasiText.setTextColor(Color.DKGRAY);
        notifikasiText.setPadding(8, 0, 8, 10);

        root.addView(notifikasiText);

        // ========================================================
        // LAPOR CEPAT KE POSKO
        // ========================================================

        Button laporButton =
                buatTombol("🚨  LAPOR CEPAT KE POSKO");

        laporButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                telepon(POSKO_HP);
            }
        });

        root.addView(laporButton);

        // ========================================================
        // HUBUNGI POSKO
        // ========================================================

        Button hubungiButton =
                buatTombol("📞  HUBUNGI POSKO");

        hubungiButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tampilkanKontakPosko();
            }
        });

        root.addView(hubungiButton);

        // ========================================================
        // STATUS PERSONEL
        // ========================================================

        Button statusButton =
                buatTombol("🟢  STATUS PERSONEL");

        statusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tampilkanStatusPersonel();
            }
        });

        root.addView(statusButton);

        // ========================================================
        // REGU & PERSONEL
        // ========================================================

        Button reguButton =
                buatTombol("👥  REGU & PERSONEL");

        reguButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tampilkanReguPersonel();
            }
        });

        root.addView(reguButton);

        // ========================================================
        // HT DIGITAL / PTT
        // ========================================================

        Button pttButton =
                buatTombol("📻  HT DIGITAL / PTT");

        pttButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                tampilkanHTDigital();
            }
        });

        root.addView(pttButton);

        // ========================================================
        // SCROLL
        // ========================================================

        ScrollView scrollView = new ScrollView(this);

        scrollView.addView(root);

        setContentView(scrollView);
    }

    // ============================================================
    // MEMBUAT TOMBOL
    // ============================================================

    private Button buatTombol(String teks) {

        Button button = new Button(this);

        button.setText(teks);
        button.setTextSize(18);
        button.setTextColor(Color.DKGRAY);
        button.setAllCaps(false);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(0, 6, 0, 6);

        button.setLayoutParams(params);

        return button;
    }

    // ============================================================
    // TELEPON
    // ============================================================

    private void telepon(String nomor) {

        try {

            Intent intent =
                    new Intent(
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

    // ============================================================
    // HUBUNGI POSKO
    // ============================================================

    private void tampilkanKontakPosko() {

        String[] pilihan = {

                "📞 Posko Kantor - 0633 21113",

                "📱 Posko HP - +62 852-9615-0410"
        };

        AlertDialog dialog =
                new AlertDialog.Builder(this)
                        .setTitle("HUBUNGI POSKO")
                        .setItems(
                                pilihan,
                                (d, pilihanDipilih) -> {

                                    if (pilihanDipilih == 0) {

                                        telepon(POSKO_KANTOR);

                                    } else {

                                        telepon(POSKO_HP);
                                    }
                                }
                        )
                        .setNegativeButton(
                                "TUTUP",
                                null
                        )
                        .create();

        dialog.show();
    }

    // ============================================================
    // STATUS PERSONEL
    // ============================================================

    private void tampilkanStatusPersonel() {

        String[] status = {

                "🟢 SIAP",

                "🟡 OTW",

                "🔵 TIBA",

                "🟠 OPERASI",

                "⚪ SELESAI"
        };

        new AlertDialog.Builder(this)

                .setTitle("STATUS PERSONEL")

                .setItems(
                        status,
                        (dialog, pilihan) -> {

                            if (pilihan == 0) {

                                statusPersonel = "SIAP";

                            } else if (pilihan == 1) {

                                statusPersonel = "OTW";

                            } else if (pilihan == 2) {

                                statusPersonel = "TIBA";

                            } else if (pilihan == 3) {

                                statusPersonel = "OPERASI";

                            } else {

                                statusPersonel = "SELESAI";
                            }

                            perbaruiStatus();
                        }
                )

                .setNegativeButton(
                        "TUTUP",
                        null
                )

                .show();
    }

    // ============================================================
    // UPDATE STATUS
    // ============================================================

    private void perbaruiStatus() {

        String simbol = "🟢";

        if (statusPersonel.equals("OTW")) {

            simbol = "🟡";

        } else if (statusPersonel.equals("TIBA")) {

            simbol = "🔵";

        } else if (statusPersonel.equals("OPERASI")) {

            simbol = "🟠";

        } else if (statusPersonel.equals("SELESAI")) {

            simbol = "⚪";
        }

        statusText.setText(
                simbol +
                " Status personel: " +
                statusPersonel
        );

        notifikasiText.setText(
                "Status diperbarui: " +
                statusPersonel
        );

        Toast.makeText(
                this,
                "Status: " + statusPersonel,
                Toast.LENGTH_SHORT
        ).show();
    }

    // ============================================================
    // REGU & PERSONEL
    // ============================================================

    private void tampilkanReguPersonel() {

        String[] pos = {

                "🚒 POS DAMKAR TARUTUNG",

                "🚒 POS DAMKAR SIBORONG-BORONG",

                "🚒 POS DAMKAR PAHAE",

                "🚒 POS DAMKAR PANGARIBUAN"
        };

        new AlertDialog.Builder(this)

                .setTitle("REGU & PERSONEL")

                .setItems(
                        pos,
                        (dialog, pilihan) -> {

                            if (pilihan == 0) {

                                pilihRegu(
                                        "Tarutung"
                                );

                            } else if (pilihan == 1) {

                                pilihRegu(
                                        "Siborong-borong"
                                );

                            } else if (pilihan == 2) {

                                pilihRegu(
                                        "Pahae"
                                );

                            } else {

                                pilihRegu(
                                        "Pangaribuan"
                                );
                            }
                        }
                )

                .setNegativeButton(
                        "TUTUP",
                        null
                )

                .show();
    }

    // ============================================================
    // PILIH REGU
    // ============================================================

    private void pilihRegu(String namaPos) {

        String[] regu = {

                "Regu 1",

                "Regu 2",

                "Regu 3"
        };

        new AlertDialog.Builder(this)

                .setTitle(
                        "POS DAMKAR " +
                        namaPos.toUpperCase()
                )

                .setItems(
                        regu,
                        (dialog, pilihan) -> {

                            reguPersonel =
                                    regu[pilihan] +
                                    " - Pos Damkar " +
                                    namaPos;

                            reguText.setText(
                                    "👥 Regu: " +
                                    reguPersonel
                            );

                            notifikasiText.setText(
                                    "Regu diperbarui: " +
                                    reguPersonel
                            );

                            Toast.makeText(
                                    this,
                                    "Regu dipilih: " +
                                    reguPersonel,
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                )

                .setNegativeButton(
                        "KEMBALI",
                        null
                )

                .show();
    }

    // ============================================================
    // HT DIGITAL / PTT
    // ============================================================

    private void tampilkanHTDigital() {

        String[] pilihan = {

                "📡 Status koneksi",

                "🎙️ TEKAN & BICARA",

                "👥 Pilih kanal/regu",

                "📢 Semua personel"
        };

        new AlertDialog.Builder(this)

                .setTitle("HT DIGITAL / PTT")

                .setItems(
                        pilihan,
                        (dialog, pilihanDipilih) -> {

                            if (pilihanDipilih == 0) {

                                tampilkanStatusKoneksi();

                            } else if (pilihanDipilih == 1) {

                                tampilkanPTT();

                            } else if (pilihanDipilih == 2) {

                                pilihKanalPTT();

                            } else {

                                tampilkanSemuaPersonel();
                            }
                        }
                )

                .setNegativeButton(
                        "TUTUP",
                        null
                )

                .show();
    }

    // ============================================================
    // STATUS KONEKSI PTT
    // ============================================================

    private void tampilkanStatusKoneksi() {

        new AlertDialog.Builder(this)

                .setTitle(
                        "STATUS HT DIGITAL"
                )

                .setMessage(
                        "Status: BELUM TERHUBUNG\n\n" +
                        "Koneksi antarperangkat PTT " +
                        "akan menggunakan server " +
                        "komunikasi internet.\n\n" +
                        "Tahap berikutnya adalah " +
                        "membangun server PTT agar " +
                        "personel dapat berbicara " +
                        "antar-HP secara realtime."
                )

                .setPositiveButton(
                        "OK",
                        null
                )

                .show();
    }

    // ============================================================
    // PTT
    // ============================================================

    private void tampilkanPTT() {

        new AlertDialog.Builder(this)

                .setTitle(
                        "🎙️ TEKAN & BICARA"
                )

                .setMessage(
                        "Fungsi PTT siap digunakan " +
                        "setelah server komunikasi " +
                        "terhubung.\n\n" +

                        "Tekan dan tahan tombol " +
                        "untuk berbicara.\n\n" +

                        "Lepaskan tombol untuk " +
                        "mengakhiri transmisi."
                )

                .setPositiveButton(
                        "SIAP",
                        null
                )

                .show();
    }

    // ============================================================
    // PILIH KANAL PTT
    // ============================================================

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

                .setTitle(
                        "PILIH KANAL HT"
                )

                .setItems(
                        kanal,
                        (dialog, pilihan) -> {

                            Toast.makeText(
                                    this,
                                    "Kanal: " +
                                    kanal[pilihan],
                                    Toast.LENGTH_SHORT
                            ).show();
                        }
                )

                .setNegativeButton(
                        "TUTUP",
                        null
                )

                .show();
    }

    // ============================================================
    // SEMUA PERSONEL
    // ============================================================

    private void tampilkanSemuaPersonel() {

        new AlertDialog.Builder(this)

                .setTitle(
                        "📢 SEMUA PERSONEL"
                )

                .setMessage(
                        "Kanal komunikasi seluruh " +
                        "personel Damkar Taput.\n\n" +

                        "Fungsi suara realtime akan " +
                        "diaktifkan setelah server " +
                        "PTT terhubung."
                )

                .setPositiveButton(
                        "OK",
                        null
                )

                .show();
    }
}