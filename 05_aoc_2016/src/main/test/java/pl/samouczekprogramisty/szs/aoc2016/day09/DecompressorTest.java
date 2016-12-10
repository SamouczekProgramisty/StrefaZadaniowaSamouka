package pl.samouczekprogramisty.szs.aoc2016.day09;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecompressorTest {

    @Test
    public void shouldDecompressSampleData() {
        String compressed = "ADVENT";
        assertEquals("ADVENT", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressSampleData1() {
        String compressed = "A(1x5)BC";
        assertEquals("ABBBBBC", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressSampleData2() {
        String compressed = "(3x3)XYZ";
        assertEquals("XYZXYZXYZ", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressSampleData3() {
        String compressed = "A(2x2)BCD(2x2)EFG";
        assertEquals("ABCBCDEFEFG", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressSampleData4() {
        String compressed = "(6x1)(1x3)A";
        assertEquals("(1x3)A", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressSampleData5() {
        String compressed = "X(8x2)(3x3)ABCY";
        assertEquals("X(3x3)ABC(3x3)ABCY", Decompressor.decompressV1(compressed));
    }

    @Test
    public void shouldDecompressV2SampleData() {
        String compressed = "(3x3)XYZ";
        assertEquals("XYZXYZXYZ", Decompressor.decompressV2(compressed));
        assertEquals("XYZXYZXYZ".length(), Decompressor.decompressV2Lenght(compressed));
    }

    @Test
    public void shouldDecompressV2SampleData2() {
        String compressed = "X(8x2)(3x3)ABCY";
        assertEquals("XABCABCABCABCABCABCY", Decompressor.decompressV2(compressed));
        assertEquals("XABCABCABCABCABCABCY".length(), Decompressor.decompressV2Lenght(compressed));
    }

    @Test
    public void shouldDecompressV2SampleData3() {
        String compressed = "(27x12)(20x12)(13x14)(7x10)(1x12)A";
        assertEquals(241920, Decompressor.decompressV2(compressed).length());
        assertEquals(241920, Decompressor.decompressV2Lenght(compressed));
    }

    @Test
    public void shouldDecompressV2SampleData4() {
        String compressed = "(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN";
        assertEquals(445, Decompressor.decompressV2(compressed).length());
        assertEquals(445, Decompressor.decompressV2Lenght(compressed));
    }
}