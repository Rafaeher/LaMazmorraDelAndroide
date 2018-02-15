package testSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import negocio.SAClienteImpTest;
import negocio.SAProductoImpTest;
import negocio.SAVentaImpTest;

@RunWith(Suite.class)
@SuiteClasses({SAProductoImpTest.class, SAClienteImpTest.class, SAVentaImpTest.class})
public class TestSuite {}
