package org.girsang.pos.controller

import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.util.Duration
import org.girsang.pos.config.AppContext
import org.girsang.pos.service.KaryawanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.ResourceBundle

@Component
class AppController (
    @Autowired
    private val karyawanService: KaryawanService
) : Initializable {

    @FXML private lateinit var labelStatus: Label
    @FXML private lateinit var labelJam: Label
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")

    @FXML fun onMenuKaryawan(event: ActionEvent){
        val loader= FXMLLoader(javaClass.getResource("/org/girsang/pos/view/karyawan.fxml"))
        val controller = loader.getController<KaryawanController>()
        loader.setControllerFactory { clazz ->
            AppContext.getBean(clazz) // Helper Spring untuk ambil bean
        }

        val root = loader.load<Parent>()
        val stage = Stage()
        stage.title = "Data Karyawan"
        stage.scene = Scene(root)
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.showAndWait()


    }
    @FXML
    fun onMenuProduk(event: ActionEvent) {
        labelStatus.text = "Form Produk dipilih"
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Menu Produk"
        alert.contentText = "Di sini akan ditampilkan form Produk."
        alert.showAndWait()
    }

    @FXML
    fun onMenuPenjualan(event: ActionEvent) {
        labelStatus.text = "Form Penjualan dipilih"
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "Menu Penjualan"
        alert.contentText = "Di sini akan ditampilkan form Penjualan."
        alert.showAndWait()
    }

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        val timeline = Timeline(
            KeyFrame(Duration.seconds(0.0), EventHandler {
                labelJam.text = LocalTime.now().format(formatter)
            }),
            KeyFrame(Duration.seconds(1.0))

        )
        timeline.cycleCount = Timeline.INDEFINITE
        timeline.play()
        loadKaryawan()
    }

    private fun loadKaryawan(){
        val karyawans =karyawanService.findAll()

    }
}