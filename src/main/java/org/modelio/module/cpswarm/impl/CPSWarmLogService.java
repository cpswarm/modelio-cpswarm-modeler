package org.modelio.module.cpswarm.impl;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.context.log.ILogService;
import org.modelio.module.sysml.impl.SysMLModule;

/**
 * Proxy for the Modelio {@link ILogService}, configuring the ModelingWizardMdac.
 */
@objid ("ff732306-4a07-4d4c-98f9-63f5a5bb8a32")
public class CPSWarmLogService {
    @objid ("e8b7169d-3b1c-414a-bf63-c63247e567ab")
    private ILogService logService;

    /**
     * Default constructor.
     * @param logService the Modelio log service.
     * @param module the current instance of {@link SysMLModule}.
     */
    @objid ("09988294-26b8-4ece-add0-6bbb15d3c026")
    public CPSWarmLogService(ILogService logService, CPSWarmModule module) {
        this.logService = logService;
    }

    /**
     * Output an information message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param msg a message to be displayed as a log.
     */
    @objid ("e5e3bd39-cd65-49f3-9eb1-4f54d8862de6")
    public void info(final String msg) {
        this.logService.warning(msg);
    }

    /**
     * Output a warning message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param msg a message to be displayed as a log.
     */
    @objid ("65900bd6-5806-4be3-9294-065a4e3513bf")
    public void warning(final String msg) {
        this.logService.warning(msg);
    }

    /**
     * Output an error message in the Modelio console.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param msg a message to be displayed as a log.
     */
    @objid ("3ac46554-7efa-4861-b518-0a3568e4d742")
    public void error(final String msg) {
        this.logService.error(msg);
    }

    /**
     * Log the given exception with its stack trace as an information.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    @objid ("90daa0c9-1cd1-41a6-b93d-2ff554da6505")
    public void info(final Throwable e) {
        this.logService.info( e);
    }

    /**
     * Log the given exception with its stack trace as a warning.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    @objid ("8d55c19f-5b33-4d66-9b02-22b2e783e930")
    public void warning(final Throwable e) {
        this.logService.warning(e);
    }

    /**
     * Log the given exception with its stack trace as an error.
     * <p>
     * This method send logs on Modelio console only if the logs have been activated. The file and line of the log is
     * displayed in the Modelio console before the message.
     * @param t an exception to be displayed as a log.
     */
    @objid ("344f759c-e2ad-4d79-b6ac-12d9f5327ff3")
    public void error(final Throwable e) {
        this.logService.error(e);
    }

}
