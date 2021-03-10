package de.hybris.questions.setup;

import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.servicelayer.impex.ImportConfig;
import de.hybris.platform.servicelayer.impex.ImportResult;
import de.hybris.platform.servicelayer.impex.ImportService;
import de.hybris.platform.servicelayer.impex.impl.StreamBasedImpExResource;
import de.hybris.questions.constants.QuestionsConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

@SystemSetup(extension = QuestionsConstants.EXTENSIONNAME)
public class QuestionsCustomSetup {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionsCustomSetup.class);
    private ImportService importService;

    public ImportService getImportService() {
        return importService;
    }
    public void setImportService(ImportService importService) {
        this.importService = importService;
    }

    @SystemSetup(type = SystemSetup.Type.PROJECT)
    public boolean createProjectData() {
        LOG.info("Starting custom project data loading for the Questions extension");
        impexImport("/questions/import/stores/electronics/solr.impex");
        impexImport("/questions/import/productCatalogs/electronicsProductCatalog/questions.impex");
        impexImport("/questions/import/contentCatalogs/electronicsContentCatalog/cms-content.impex");
        LOG.info("Custom project data loading for the Questions extension completed.");
        return true;
    }

    protected boolean impexImport(String filename) {
        final String message = "Questions impexing [" + filename + "]...";
        try (final InputStream resourceAsStream = getClass().getResourceAsStream(filename)) {
            LOG.info(message);
            final ImportConfig importConfig = new ImportConfig();
            importConfig.setScript(new StreamBasedImpExResource(resourceAsStream, "UTF-8"));
            importConfig.setLegacyMode(Boolean.FALSE);
            final ImportResult importResult = getImportService().importData(importConfig);
            if (importResult.isError()) {
                LOG.error(message + " FAILED");
                return false;
            }
        }
        catch (final Exception e) {
            LOG.error(message + " FAILED", e);
            return false;
        }
        return true;
    }

}
