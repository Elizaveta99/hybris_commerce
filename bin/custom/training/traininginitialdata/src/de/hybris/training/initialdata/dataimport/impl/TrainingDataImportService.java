package de.hybris.training.initialdata.dataimport.impl;

import de.hybris.platform.commerceservices.dataimport.impl.SampleDataImportService;

public class TrainingDataImportService extends SampleDataImportService
{
    @Override
    protected void importProductCatalog(final String extensionName, final String productCatalogName)
    {
        super.importProductCatalog(extensionName, productCatalogName);
        //Load User Price Groups
        getSetupImpexService().importImpexFile(
                "/traininginitialdata/import/sampledata/productCatalogs/catalogName/pricegroups.impex", false);
    }
}
