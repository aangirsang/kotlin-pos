package org.girsang.pos.controller

import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.util.Callback
import org.girsang.pos.config.AppContext
import org.girsang.pos.model.Jabatan
import org.girsang.pos.model.Karyawan
import org.girsang.pos.service.JabatanService
import org.girsang.pos.service.KaryawanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL
import java.time.LocalDate
import java.util.ResourceBundle


@Component
class KaryawanController (
    @Autowired private val karyawanService: KaryawanService,
    @Autowired private val jabatanService: JabatanService
) : Initializable{



    @FXML private lateinit var karyawanTable: TableView<Karyawan>
    @FXML private lateinit var idKolom: TableColumn<Karyawan, Long>
    @FXML private lateinit var noKaryawanKolom: TableColumn<Karyawan, String>
    @FXML private lateinit var namaKaryawanKolom: TableColumn<Karyawan, String>
    @FXML private lateinit var tmpLahirKaryawanKolom: TableColumn<Karyawan, String>
    @FXML private lateinit var tglLahirKaryawanKolom: TableColumn<Karyawan, LocalDate>
    @FXML private lateinit var jenisKelaminKolom: TableColumn<Karyawan, String>
    @FXML private lateinit var jabatanKolom: TableColumn<Karyawan, Jabatan>

    @FXML private lateinit var cboJabatan: ComboBox<Jabatan>

    @FXML fun onMenuJabatan(){
        val loader = FXMLLoader(javaClass.getResource("/org/girsang/pos/view/jabatan.fxml"))
        loader.controllerFactory = Callback { AppContext.getBean(it) }
        val root = loader.load<Parent>()
        val stage = Stage()
        stage.title = "Jabatan"
        stage.scene = Scene(root)
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.showAndWait()
        loadComboBox()
    }
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        loadData()
        loadComboBox()
    }
    private fun loadData() {
        val karyawanList = karyawanService.findAll()
        //karyawanTable.items = FXCollections.observableArrayList(karyawanList)
    }
    private fun loadComboBox() {
        val jabatanList = jabatanService.findAll() // misalnya kembalikan List<Jabatan>
        cboJabatan.items = FXCollections.observableArrayList(jabatanList)
        cboJabatan.selectionModel.clearSelection()
    }
}