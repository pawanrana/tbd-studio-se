// ============================================================================
//
// Copyright (C) 2006-2017 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.hadoop.distribution.cdh5130.test.modulegroup.node.sparkstreaming;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.talend.hadoop.distribution.DistributionModuleGroup;
import org.talend.hadoop.distribution.cdh5130.CDH5130Constant;
import org.talend.hadoop.distribution.cdh5130.CDH5130Distribution;
import org.talend.hadoop.distribution.cdh5130.modulegroup.node.sparkstreaming.CDH5130SparkStreamingKafkaAvroModuleGroup;

public class CDH5130SparkStreamingKafkaAvroModuleGroupTest {

    @Test
    public void testModuleGroups() throws Exception {
        Map<String, String> results = new HashMap<>();
        results.put(
                CDH5130Constant.SPARK2_KAFKA_ASSEMBLY_MRREQUIRED_MODULE_GROUP.getModuleName(),
                "(((#LINK@NODE.STORAGE_CONFIGURATION.DISTRIBUTION=='CLOUDERA') AND (#LINK@NODE.STORAGE_CONFIGURATION.SPARK_VERSION=='Cloudera_CDH5_13')) AND (#LINK@NODE.STORAGE_CONFIGURATION.SPARK_LOCAL_MODE=='false') AND (#LINK@NODE.STORAGE_CONFIGURATION.SUPPORTED_SPARK_VERSION=='SPARK_2_2_0'))"); //$NON-NLS-1$
        results.put(
                CDH5130Constant.SPARK_KAFKA_AVRO_MRREQUIRED_MODULE_GROUP.getModuleName(),
                "((#LINK@NODE.STORAGE_CONFIGURATION.DISTRIBUTION=='CLOUDERA') AND (#LINK@NODE.STORAGE_CONFIGURATION.SPARK_VERSION=='Cloudera_CDH5_13')) AND (#LINK@NODE.STORAGE_CONFIGURATION.SPARK_LOCAL_MODE=='false')"); //$NON-NLS-1$

        Set<DistributionModuleGroup> moduleGroups = CDH5130SparkStreamingKafkaAvroModuleGroup.getModuleGroups(
                CDH5130Distribution.DISTRIBUTION_NAME, CDH5130Distribution.VERSION);
        assertEquals(results.size(), moduleGroups.size());
        moduleGroups.iterator();
        for (DistributionModuleGroup module : moduleGroups) {
            assertTrue("Should contain module " + module.getModuleName(), results.containsKey(module.getModuleName())); //$NON-NLS-1$
            if (results.get(module.getModuleName()) == null) {
                assertTrue("The condition of the module " + module.getModuleName() + " is not null.", //$NON-NLS-1$ //$NON-NLS-2$
                        results.get(module.getModuleName()) == null);
            } else {
                assertTrue("The condition of the module " + module.getModuleName() + " is null, but it should be " //$NON-NLS-1$ //$NON-NLS-2$
                        + results.get(module.getModuleName()) + ".", results.get(module.getModuleName()) != null); //$NON-NLS-1$
                assertEquals(results.get(module.getModuleName()), module.getRequiredIf().getConditionString());
            }
        }
    }
}
