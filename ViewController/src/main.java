import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.view.rich.event.DialogEvent;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.event.PopupFetchEvent;
import model.services.AppModuleImpl;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import oracle.jbo.server.ViewObjectImpl;

import org.apache.myfaces.trinidad.event.SelectionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.jbo.ApplicationModule;

public class main {
    private RichTable linetable;
    private RichTable sizetable;

    public main() {
    }
    
    public ApplicationModule getAppM() {
          DCBindingContainer bindingContainer =
              (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
          //BindingContext bindingContext = BindingContext.getCurrent();
          DCDataControl dc =
              bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
          AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
          return appM;
      }
      AppModuleImpl am = (AppModuleImpl)this.getAppM();
    
    

    public void dialogokListenerForFIlldata(DialogEvent dialogEvent) {
        // Add event code here...
        
        System.out.println("inside popup dialog selection");
                       if (dialogEvent.getOutcome().name().equals("ok")) {
                           ViewObject populatevo = am.getfiilDataVO1();
                           // populatevo.executeQuery();

                           Row[] r = populatevo.getAllRowsInRange();
                           System.out.println("Drop ------->" + r.length);
                           for (Row row : r) {

                               if (row.getAttribute("flag") != null &&
                                   row.getAttribute("flag").equals("y")) {
                                   System.out.println("flag --->" + row.getAttribute("flag"));
                                   popSizeAll(row);
                               }
                           }
                           AdfFacesContext.getCurrentInstance().addPartialTarget(linetable);
                       } else {
                           System.out.println("no selected");
                       }
        
    }
    
    
    
    
    public void popSizeAll(Row poprow) {
              //// here fill value is taking form fill Size popup And inserting below table
System.out.println("i am in pop up size for fill data");
              Row linerow =getRollLine();
              linerow.setAttribute("SystemId", getPopulateValue(poprow, "SystemId"));
              linerow.setAttribute("Quantity",
                                   getPopulateValue(poprow, "ReceiveQty"));
              linerow.setAttribute("OrgId",
                                   getPopulateValue(poprow, "LcUnit"));


          } 
    
    public String getPopulateValue(Row r, String columnName) {

           String value = null;
           try {
               value = r.getAttribute(columnName).toString();
           } catch (Exception e) {
               ;
           }
           return value;
       }
    public Row getRollLine() {

           ViewObject vo = am.getXxOdmIssueToFinishLTVO2();
           Row row = vo.createRow();
           vo.insertRow(row);
           row.setNewRowState(Row.STATUS_INITIALIZED);
           return row;
       } //end of createHeader


    public void dialogListenerforFillsize(DialogEvent dialogEvent) {
        // Add event code here.
        
        System.out.println("inside popup dialog selection2");
                       if (dialogEvent.getOutcome().name().equals("ok")) {
                           ViewObject populatevo = am.getfiilDataVO1();
                           // populatevo.executeQuery();

                           Row[] r = populatevo.getAllRowsInRange();
                           System.out.println("Drop ------->" + r.length);
                           for (Row row : r) {

                               if (row.getAttribute("flag") != null &&
                                   row.getAttribute("flag").equals("y")) {
                                   System.out.println("flag --->" + row.getAttribute("flag"));
                                   popSizeAll2(row);
                               }
                           }
                          AdfFacesContext.getCurrentInstance().addPartialTarget(sizetable);
                       } else {
                           System.out.println("no selected");
                       }
    
    
    
    }
    
    
    
    
    
    
    
    public void popSizeAll2(Row poprow) {
              //// here fill value is taking form fill Size popup And inserting below table
              System.out.println("i am in pop up size for fill size");
              Row linerow =getRollLine2();
              linerow.setAttribute("Inseam", getPopulateValue(poprow, "Character3"));
              linerow.setAttribute("SizeInseam",
                                   getPopulateValue(poprow, "Character2"));
              linerow.setAttribute("Quantity",
                                   getPopulateValue(poprow, "LcUnit"));


          } 
    
    
    
    
    
    
    public Row getRollLine2() {

           ViewObject vo = am.getXxOdmIssueToFinishDTVO3();
           Row row = vo.createRow();
           vo.insertRow(row);
           row.setNewRowState(Row.STATUS_INITIALIZED);
           return row;
       } //end of createHeader

    public void LinePOP(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        ViewObject vo = am.getfiilDataVO1();
        vo.executeQuery();
        
    }

    public void DetailPopUp(PopupFetchEvent popupFetchEvent) {
        // Add event code here...
        ViewObject line =am.getXxOdmIssueToFinishLTVO2();
        
       String system_id=null;
        try {
                   system_id = line.getCurrentRow().getAttribute("SystemId").toString();
               } catch (Exception e) {
            String message = e.getMessage();
            showMessage(message,"info");
        }
        ViewObject oder = am.getIseamSizePOPUPVO1();
               oder.setWhereClause("SYSTEM_ID= '"+system_id+"'");
        oder.executeQuery();
    }

    public void setLinetable(RichTable linetable) {
        this.linetable = linetable;
    }

    public RichTable getLinetable() {
        return linetable;
    }

    public void setSizetable(RichTable sizetable) {
        this.sizetable = sizetable;
    }

    public RichTable getSizetable() {
        return sizetable;
    }
    
    public void showMessage(String message, String severity) {

           FacesMessage fm = new FacesMessage(message);

           if (severity.equals("info")) {
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               System.out.println("inside message");
           } else if (severity.equals("warn")) {
               fm.setSeverity(FacesMessage.SEVERITY_WARN);
           } else if (severity.equals("error")) {
               fm.setSeverity(FacesMessage.SEVERITY_ERROR);
           }

           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, fm);

       }

    public void CustomSave(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject line =am.getXxOdmIssueToFinishLTVO2();
        ViewObject vo = am.getXxOdmIssueToFinishDTVO3();
     am.getDBTransaction().commit();
     line.executeQuery();
     vo.executeQuery();
    }
}
