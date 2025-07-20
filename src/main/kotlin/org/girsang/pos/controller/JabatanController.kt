package org.girsang.pos.controller

import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.property.ReadOnlyStringWrapper
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.input.MouseEvent
import org.girsang.pos.model.Jabatan
import org.girsang.pos.service.JabatanService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.net.URL
import java.util.ResourceBundle

@Component
class JabatanController(
    @Autowired
    private val jabatanService: JabatanService
) : Initializable {

    @FXML private lateinit var tblJabatan : TableView<Jabatan>
    @FXML private lateinit var idKolom: TableColumn<Jabatan, Long>
    @FXML private lateinit var jabatanKolom: TableColumn<Jabatan, String>

    @FXML private lateinit var  jabatanTxt: TextField

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        val jabatanList = jabatanService.findAll()
        println("Jabatan Jalan")

        idKolom.setCellValueFactory { ReadOnlyObjectWrapper(it.value.id) }
        jabatanKolom.setCellValueFactory { ReadOnlyStringWrapper(it.value.jabatan)}
        loadData()
    }

    @FXML private fun handleTableClick(event: MouseEvent){
        val selected = tblJabatan.selectionModel.selectedItem
        if(selected!= null){
            val jabatan = jabatanService.findById(selected.id)
            jabatanTxt.text = jabatan.get().jabatan
        }
    }

    @FXML private fun onSimpanJabatan(){
        if (jabatanTxt.text == "") return
        val selectedTable = tblJabatan.selectionModel.selectedItem
        if(selectedTable == null){
            val jabatanData = jabatanTxt.text.toString()
            val jabatan = Jabatan(jabatan = jabatanData)
            jabatanService.save(jabatan)
            println("Jabatan $jabatan Disimpan")
            loadData()
        }else{
            var jabatan = jabatanService.findById(selectedTable.id)
            if(jabatan != null){
                val id = selectedTable.id
                val jabatanData = jabatanTxt.text
                val jabatan = Jabatan(id = id, jabatan = jabatanData)
                jabatanService.save(jabatan)
                println("Jabatan ${jabatan.jabatan} Dirubah")
                loadData()
            }
        }
    }

    @FXML private fun onHapusJabatan(){
        val selected = tblJabatan.selectionModel.selectedItem
        if(selected != null){
            var jabatan = jabatanService.findById(selected.id)
            if(jabatan != null){
                jabatanService.delete(selected.id)
                loadData()
                println("Jabatan ${selected.jabatan} Dihapus")
            }
        }
    }

    private fun loadData(){
        val jabatans = jabatanService.findAllByOrderByJabatanAsc()
        tblJabatan.items = FXCollections.observableArrayList(jabatans)
        tblJabatan.selectionModel.clearSelection()
        jabatanTxt.clear()

    }
}