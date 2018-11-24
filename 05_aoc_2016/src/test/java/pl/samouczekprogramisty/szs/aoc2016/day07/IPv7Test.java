package pl.samouczekprogramisty.szs.aoc2016.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IPv7Test {
    @Test
    public void shouldSayThatSupportsTLS() {
        IPv7 ipv7 = new IPv7("abba[mnop]qrst");
        assertTrue(ipv7.supportsTLS());
    }

    @Test
    public void shouldSayThatSupportsTLS2() {
        IPv7 ipv7 = new IPv7("qrst[mnop]abba");
        assertTrue(ipv7.supportsTLS());
    }

    @Test
    public void shouldSayThatSupportsTLS3() {
        IPv7 ipv7 = new IPv7("ioxxoj[asdfgh]zxcvbn");
        assertTrue(ipv7.supportsTLS());
    }

    @Test
    public void shouldSayThatDoesntSupportTLS() {
        IPv7 ipv7 = new IPv7("abcd[bddb]xyyx");
        assertFalse(ipv7.supportsTLS());
    }

    @Test
    public void shouldSayThatDoesntSupportTLS2() {
        IPv7 ipv7 = new IPv7("aaaa[qwer]tyui");
        assertFalse(ipv7.supportsTLS());
    }

    @Test
    public void shouldSayThatSupportsSSL() {
        IPv7 ipv7 = new IPv7("aba[bab]xyz");
        assertTrue(ipv7.supportsSSL());
    }

    @Test
    public void shouldSayThatSupportsSSL2() {
        IPv7 ipv7 = new IPv7("aaa[kek]eke");
        assertTrue(ipv7.supportsSSL());
    }

    @Test
    public void shouldSayThatSupportsSSL3() {
        IPv7 ipv7 = new IPv7("zazbz[bzb]cdb");
        assertTrue(ipv7.supportsSSL());
    }

    @Test
    public void shouldSayThatDoesntSupportsSSL() {
        IPv7 ipv7 = new IPv7("xyx[xyx]xyx");
        assertFalse(ipv7.supportsSSL());
    }
}