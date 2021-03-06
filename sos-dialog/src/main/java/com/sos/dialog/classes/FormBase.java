package com.sos.dialog.classes;

import static com.sos.dialog.swtdesigner.SWTResourceManager.getCursor;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.sos.JSHelper.Basics.JSToolBox;
import com.sos.dialog.comparators.SortBaseComparator;
// import com.sos.dialog.swtdesigner.SWTResourceManager;
import com.sos.hibernate.classes.SosSortTableItem;

/** @author KB */
public class FormBase extends JSToolBox {

    protected Composite objParent = null;
    protected Shell shell = null;
    protected Cursor objLastCursor = null;
    protected int colPosForSort;
    protected boolean colSortFlag = false;
    private boolean rightMouseclick;
    public static final int RIGHT_MOUSE_BUTTON = 3;

    protected FormBase() {
        //
    }

    public FormBase(Composite pParentComposite, String className) {
        super(className);
        objParent = pParentComposite;
        shell = pParentComposite.getShell();
        pParentComposite.setLayout(new GridLayout());
    }

    protected void setResizableV(Control objControl) {
        boolean flgGrapVerticalspace = true;
        objControl.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, flgGrapVerticalspace));
    }

    public void clearTable(Table table) {
        table.clearAll();
        int l = table.getItemCount();
        for (int i = 0; i < l; i++) {
            TableItem t = table.getItem(table.getItemCount() - 1);
            t.dispose();
        }
    }

    public int getIntValue(String s, int d) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException n) {
            return d;
        }
    }

    protected void showWaitCursor() {
        if (!shell.isDisposed()) {
            objLastCursor = shell.getCursor();
        }
        shell.setCursor(getCursor(SWT.CURSOR_WAIT));
    }

    protected void resetCursor() {
        shell.setCursor(getCursor(SWT.CURSOR_ARROW));
        objLastCursor = null;
    }

    protected void restoreCursor() {
        if (!shell.isDisposed()) {
            if (objLastCursor == null) {
                shell.setCursor(getCursor(SWT.CURSOR_ARROW));
            } else {
                shell.setCursor(objLastCursor);
                objLastCursor = null;
            }
        }
    }

    public boolean isRightMouseclick() {
        return rightMouseclick;
    }

    public void setRightMausclick(boolean b) {
        rightMouseclick = b;
    }

    public void setRightMausclick(Event event) {
        setRightMausclick((event.button == RIGHT_MOUSE_BUTTON));
    }

    protected Shell getShell() {
        return shell;
    }

    protected final void sortTable(Table table, SortBaseComparator[][] comparables) {
        if (comparables != null) {
            for (int i = 0; i < comparables[colPosForSort].length; i++) {
                comparables[colPosForSort][i]._sortFlag = colSortFlag;
            }
            Arrays.sort(comparables[colPosForSort]);
            SosSortTableItem tmpSortTableItem = new SosSortTableItem();
            for (int i = 0; i < table.getItemCount(); i++) {
                tmpSortTableItem.setTextBuffer(comparables[colPosForSort][i].sosSortTableItem.getTextBuffer());
                tmpSortTableItem.setBackground(comparables[colPosForSort][i].sosSortTableItem.getBackground());
                tmpSortTableItem.setForeground(comparables[colPosForSort][i].sosSortTableItem.getForeground());
                tmpSortTableItem.setBackgroundColumn(comparables[colPosForSort][i].sosSortTableItem.getBackgroundColumn());
                tmpSortTableItem.setForegroundColumn(comparables[colPosForSort][i].sosSortTableItem.getForegroundColumn());
                tmpSortTableItem.setData(comparables[colPosForSort][i].sosSortTableItem.getData());
                table.getItems()[i].setText(tmpSortTableItem.getTextBuffer());
                table.getItems()[i].setBackground(tmpSortTableItem.getBackground());
                table.getItems()[i].setForeground(tmpSortTableItem.getForeground());
                if (tmpSortTableItem.getBackgroundColumn() != null) {
                    for (int ii = 0; ii < tmpSortTableItem.getBackgroundColumn().length; ii++) {
                        table.getItems()[i].setBackground(ii, tmpSortTableItem.getBackgroundColumn()[ii]);
                        table.getItems()[i].setForeground(ii, tmpSortTableItem.getForegroundColumn()[ii]);
                    }
                }
                table.getItems()[i].setData(tmpSortTableItem.getData());
            }
        }
    }

}