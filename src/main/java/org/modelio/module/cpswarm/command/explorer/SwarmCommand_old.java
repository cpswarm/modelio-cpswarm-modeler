package org.modelio.module.cpswarm.command.explorer;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.ITransaction;
import org.modelio.api.module.IModule;
import org.modelio.api.module.command.DefaultModuleCommandHandler;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.module.cpswarm.utils.CPSWarmResourcesManager;
import org.modelio.vcore.smkernel.mapi.MObject;

public class SwarmCommand_old extends DefaultModuleCommandHandler {
     String title = "Complete";

     String description = "Generation Complete";

    @Override
    public void actionPerformed(List<MObject> selectedElements, IModule module) {
//        final Composite composite = toolkit.createComposite(section, SWT.WRAP);
//        GridLayout layout = new GridLayout();
//        layout.numColumns = 2;
//        composite.setLayout(layout);
//        
//        final Table table = toolkit.createTable(composite, SWT.BORDER | SWT.FULL_SELECTION);
//        this.modulesTable = new TableViewer(table);
//        table.setHeaderVisible(true);
//        
//        GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true);
//        gd.heightHint = 180;
//        gd.minimumWidth = 300;
//        table.setLayoutData(gd);
//        
//        this.modulesTable.setContentProvider(new ArrayContentProvider());
//        
//        TableViewerColumn enableColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
//        enableColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.EnableColumn")); //$NON-NLS-1$
//        enableColumn.getColumn().setWidth(30);
//        enableColumn.setEditingSupport(new ModuleStateEditingSupport(this.modulesTable, this.moduleService));
//        enableColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                return ""; //$NON-NLS-1$
//            }
//        
//            @Override
//            public Image getImage(Object element) {
//                if (element instanceof GModule) {
//                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
//                    if (iModule != null && iModule.getState() == ModuleRuntimeState.Started) {
//                        return ModulesSection.CHECKED;
//                    }
//                }
//                return ModulesSection.UNCHECKED;
//            }
//        });
//        
//        TableViewerColumn scopeColumn = new TableViewerColumn(this.modulesTable, SWT.NONE);
//        scopeColumn.getColumn().setWidth(120);
//        scopeColumn.getColumn().setResizable(true);
//        scopeColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.ScopeColumn")); //$NON-NLS-1$
//        scopeColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                if (element instanceof GModule) {
//                    return ScopeHelper.getText(((GModule) element).getScope());
//                }
//                return ""; //$NON-NLS-1$
//            }
//        });
//        
//        TableViewerColumn labelColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
//        labelColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.NameColumn")); //$NON-NLS-1$
//        labelColumn.getColumn().setWidth(100);
//        labelColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                return ModuleHelper.getLabel(element, ModulesSection.this.moduleService);
//            }
//        
//            @Override
//            public Image getImage(Object element) {
//                return ModuleHelper.getIcon(element);
//            }
//        });
//        
//        TableViewerColumn versionColumn = new TableViewerColumn(this.modulesTable, SWT.RIGHT);
//        versionColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.VersionColumn")); //$NON-NLS-1$
//        versionColumn.getColumn().setWidth(20);
//        versionColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                return ModuleHelper.getVersion(element);
//            }
//        
//            @Override
//            public Image getImage(Object element) {
//                return null;
//            }
//        });
//        
//        TableViewerColumn statusColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
//        statusColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.StatusColumn")); //$NON-NLS-1$
//        statusColumn.getColumn().setWidth(100);
//        statusColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                if (element instanceof GModule) {
//                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
//                    if (iModule != null) {
//                        String state = AppProjectConfExt.I18N
//                                .getString("ModulesSection.state." + iModule.getState().name());
//                        if (iModule.getDownError() != null) {
//                            state += AppProjectConfExt.I18N.getString("ModulesSection.state.problem");
//                        }
//                        return state;
//                    }
//                }
//                return AppProjectConfExt.I18N.getString("ModulesSection.state.Broken"); //$NON-NLS-1$
//            }
//        
//            @Override
//            public String getToolTipText(Object element) {
//                if (element instanceof GModule) {
//                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
//                    if (iModule != null) {
//                        String state = AppProjectConfExt.I18N.getString("ModulesSection." + iModule.getState().name());
//                        if (iModule.getDownError() != null) {
//                            return state + ": " + iModule.getDownError().getLocalizedMessage();
//                        } else {
//                            return state;
//                        }
//                    } else {
//                        return AppProjectConfExt.I18N.getString("ModulesSection.state.NoRTModule"); //$NON-NLS-1$
//                    }
//                }
//                return AppProjectConfExt.I18N.getMessage("ModulesSection.state.NoGModule", element); //$NON-NLS-1$
//            }
//        
//            @Override
//            public Image getImage(Object element) {
//                return null;
//            }
//        });
//        
//        TableViewerColumn licenseColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
//        licenseColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.LicenseColumn")); //$NON-NLS-1$
//        licenseColumn.getColumn().setWidth(200);
//        licenseColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                if (element instanceof GModule) {
//                    IRTModule iModule = ModulesSection.this.moduleService.getIRTModule((GModule) element);
//                    if (iModule != null) {
//                        final ILicenseInfos licenseInfos = iModule.getLicenseInfos();
//                        if (licenseInfos != null) {
//                            Date date = licenseInfos.getDate();
//                            if (date != null) {
//                                SimpleDateFormat sdf = new SimpleDateFormat(
//                                        AppProjectConfExt.I18N.getString("ModulesSection.License.DateFormat"));
//                                return AppProjectConfExt.I18N.getMessage("$ModulesSection.License." + licenseInfos.getStatus().name() + ".limited", sdf.format(date));
//                            } else {
//                                return AppProjectConfExt.I18N.getMessage("$ModulesSection.License." + licenseInfos.getStatus().name() + ".unlimited");
//                            }
//                        } else {
//                            AppProjectConfExt.LOG.warning("'" + iModule.getName() + "' (" + iModule.getClass().getName()
//                                    + ") module has no license info (state=" + iModule.getState() + ")");
//                        }
//                    }
//                }
//                return AppProjectConfExt.I18N.getString("$ModulesSection.License.UNDEFINED.unlimited"); //$NON-NLS-1$
//            }
//        
//            @Override
//            public Image getImage(Object element) {
//                return null;
//            }
//        });
//        
//        TableViewerColumn compatibilityColumn = new TableViewerColumn(this.modulesTable, SWT.LEFT);
//        compatibilityColumn.getColumn().setText(AppProjectConfExt.I18N.getString("ModulesSection.CompatibilityColumn")); //$NON-NLS-1$
//        compatibilityColumn.getColumn().setWidth(200);
//        compatibilityColumn.setLabelProvider(new ColumnLabelProvider() {
//            @Override
//            public String getText(Object element) {
//                if (element instanceof GModule) {
//                    IModuleHandle mh = ((GModule) element).getModuleHandle();
//                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
//                    case COMPATIBLE:
//                        return AppProjectConfExt.I18N.getString("ModulesSection.Compatible");
//                    case FULLYCOMPATIBLE:
//                        return AppProjectConfExt.I18N.getString("ModulesSection.FullyCompatible");
//                    case MODELIO_TOO_OLD:
//                        return AppProjectConfExt.I18N.getString("ModulesSection.ModelioTooOld");
//                    case MODULE_TOO_OLD:
//                        return AppProjectConfExt.I18N.getString("ModulesSection.ModuleTooOld");
//                    default:
//                        break;
//                    }
//                }
//                return "";
//            }
//        
//            @Override
//            public Color getForeground(Object element) {
//                if (element instanceof GModule) {
//                    IModuleHandle mh = ((GModule) element).getModuleHandle();
//                    switch (CompatibilityHelper.getCompatibilityLevel(mh)) {
//                    case COMPATIBLE:
//                        return Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
//                    case FULLYCOMPATIBLE:
//                        return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
//                    case MODELIO_TOO_OLD:
//                    case MODULE_TOO_OLD:
//                        return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
//                    default:
//                        break;
//                    }
//                }
//                return super.getForeground(element);
//            }
//        });
//        this.modulesTable.setInput(null);
//        
//        this.modulesTable.addSelectionChangedListener(new ISelectionChangedListener() {
//            @Override
//            public void selectionChanged(SelectionChangedEvent event) {
//                setInput(getProjectAdapter());
//            }
//        });
//        
//        ColumnViewerToolTipSupport.enableFor(this.modulesTable);
//        
//        // The buttons composite
//        Composite btnPanel = toolkit.createComposite(composite, SWT.NONE);
//        GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
//        btnPanel.setLayoutData(gd2);
//        btnPanel.setLayout(new GridLayout(1, false));
//        
//        // Add button
//        this.addButton = toolkit.createButton(btnPanel, "", SWT.PUSH);
//        this.addButton.setText(AppProjectConfExt.I18N.getString("ModulesSection.AddModuleButtonLabel"));
//        this.addButton.setToolTipText(AppProjectConfExt.I18N.getString("ModulesSection.AddModuleButtonToolTip"));
//        this.addButton.setEnabled(false);
//        this.addButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
//        this.addButton.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent evt) {
//                List<IModuleHandle> moduleList;
//                if ((evt.stateMask & SWT.MOD1) != 0 && AppPreferences.getPreferences().getBoolean(AppSharedPreferencesKeys.SHOWADMTOOLS_PREFKEY)) {
//                    moduleList = computeNewestModuleSelection();
//                } else {
//                    moduleList = Collections.emptyList();
//                }
//        
//                if (moduleList.isEmpty()) {
//                    moduleList = promptUserModuleSeletion();
//                }
//        
//                addSelectedModules(moduleList);
//            }
//        });
//        
//        // Delete button
//        this.removeButton = toolkit.createButton(btnPanel, "", SWT.PUSH); //$NON-NLS-1$
//        this.removeButton.setText(AppProjectConfExt.I18N.getString("ModulesSection.RemoveModuleButtonLabel"));
//        this.removeButton.setToolTipText(AppProjectConfExt.I18N.getString("ModulesSection.RemoveModuleButtonToolTip"));
//        this.removeButton.setEnabled(false);
//        this.removeButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true));
//        this.removeButton.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(SelectionEvent evt) {
//                AppProjectConfExt.LOG.debug("Remove a module"); //$NON-NLS-1$
//        
//                ModuleRemovalConfirmationDialog confirmDlg = new ModuleRemovalConfirmationDialog(
//                        ModulesSection.this.removeButton.getShell());
//                confirmDlg.setBlockOnOpen(true);
//                if (confirmDlg.open() == 0) {
//                    for (TableItem item : table.getSelection()) {
//                        final GModule module = (GModule) item.getData();
//                        try {
//                            ModulesSection.this.moduleService.removeModule(module);
//                        } catch (ModuleException e) {
//                            MessageDialog.openError(null,
//                                    AppProjectConfExt.I18N.getMessage("ModulesSection.UnableToRemoveModule", module.getName()),
//                                    e.getLocalizedMessage() + "\n" + (e.getCause() != null ? e.getCause().getLocalizedMessage() : ""));
//                            AppProjectConfExt.LOG.debug(e);
//                        } catch (AccessDeniedException e) {
//                            MessageDialog.openError(null, AppProjectConfExt.I18N.getMessage("ModulesSection.UnableToRemoveModule", module.getName()), e.getLocalizedMessage());
//                            AppProjectConfExt.LOG.debug(e);
//                        }
//                    }
//                }
//                setInput(getProjectAdapter());
//            }
//        });
        
//        action(selectedElements, module);
    }

    private void action(List<MObject> selectedElements, IModule module) {
        MObject owner = selectedElements.get(0);
        
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("Swarm", owner);
        
        String templatePath = CPSWarmResourcesManager.getPattern("Swarm_1.0.00.umlt");
        
        IModelingSession session = module.getModuleContext().getModelingSession();
        
        try(ITransaction transaction = session.createTransaction ("Swarm Creation")){
            module.getModuleContext().getModelioServices().getPatternService().applyPattern(Paths.get(templatePath), parameters);
            transaction.commit ();
            completeBox();
            
        } catch (InvalidParameterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void completeBox() {
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                customMessageBox(SWT.ICON_INFORMATION);
            }
        });
    }

    void customMessageBox(int icon) {
        MessageBox messageBox = new MessageBox(Display.getCurrent().getActiveShell(), icon);
        messageBox.setMessage(this.description);
        messageBox.setText(this.title);
        messageBox.open();
    }

    /**
     * This methods authorizes a command to be displayed in a defined context.
     * The commands are displayed, by default, depending on the kind of metaclass on which the command has been launched.
     */
    @Override
    public boolean accept(List<MObject> selectedElements, IModule module) {
        if ((selectedElements != null) && (selectedElements.size() == 1 )){
            MObject selected = selectedElements.get(0);
            return (selected instanceof org.modelio.metamodel.uml.statik.Package) &&
                    (!(selected instanceof Profile));
        }
        return false;
    }

    /**
     * This method specifies whether or not a command must be deactivated.
     * If the command has to be displayed (which means that the accept method has returned a positive value, it is sometimes needed to desactivate the command depending on specific constraints that are specific to the module.
     */
    @Override
    public boolean isActiveFor(List<MObject> selectedElements, IModule module) {
        return true;
    }

}
