package com.example.lab3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.R // <-- This import fixes the unresolved 'R' error

class MainActivity : AppCompatActivity() {
    // Use lateinit for views that will be initialized in onCreate
    private lateinit var ed_name: EditText
    private lateinit var tv_text: TextView
    private lateinit var tv_name: TextView
    private lateinit var tv_winner: TextView
    private lateinit var tv_mmora: TextView
    private lateinit var tv_cmora: TextView
    private lateinit var btn_scissor: RadioButton
    private lateinit var btn_stone: RadioButton
    private lateinit var btn_paper: RadioButton
    private lateinit var btn_mora: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 連結xml的元件
        ed_name = findViewById(R.id.ed_name)
        tv_text = findViewById(R.id.tv_text)
        tv_name = findViewById(R.id.tv_name)
        tv_winner = findViewById(R.id.tv_winner)
        tv_mmora = findViewById(R.id.tv_mmora)
        tv_cmora = findViewById(R.id.tv_cmora)
        btn_scissor = findViewById(R.id.btn_scissor)
        btn_stone = findViewById(R.id.btn_stone)
        btn_paper = findViewById(R.id.btn_paper)
        btn_mora = findViewById(R.id.btn_mora)

        btn_mora.setOnClickListener {
            // 判斷字串是否是空白,如果是,則要求用戶輸入姓名
            if (ed_name.length() < 1) {
                tv_text.text = "請輸入玩家姓名"
            } else {
                val playerName = ed_name.text.toString()

                // 顯示玩家姓名 (Optimized with String Templates)
                tv_name.text = "名字\n$playerName"

                // 我方出拳 (Optimized with 'when' expression)
                tv_mmora.text = when {
                    btn_scissor.isChecked -> "我方出拳\n剪刀"
                    btn_stone.isChecked -> "我方出拳\n石頭"
                    else -> "我方出拳\n布"
                }

                // 使用亂數產生電腦出拳 (Optimized Random generation)
                val computerRandom = (0..2).random()

                // 顯示電腦出拳
                tv_cmora.text = when (computerRandom) {
                    0 -> "電腦出拳\n剪刀"
                    1 -> "電腦出拳\n石頭"
                    else -> "電腦出拳\n布"
                }

                // 判斷勝負
                when {
                    (btn_scissor.isChecked && computerRandom == 2) ||
                            (btn_stone.isChecked && computerRandom == 0) ||
                            (btn_paper.isChecked && computerRandom == 1) -> {
                        tv_winner.text = "勝利者\n$playerName"
                        tv_text.text = "恭喜您獲勝了!!!"
                    }
                    (btn_scissor.isChecked && computerRandom == 1) ||
                            (btn_stone.isChecked && computerRandom == 2) ||
                            (btn_paper.isChecked && computerRandom == 0) -> {
                        tv_winner.text = "勝利者\n電腦"
                        tv_text.text = "可惜,電腦獲勝了!"
                    }
                    else -> {
                        tv_winner.text = "勝利者\n平手"
                        tv_text.text = "平局,請再試一次!"
                    }
                }
            }
        }
    }
}