package main.canny

enum class KernelType(val kernelMatrix: Array<IntArray>) {

    IDENTITY(arrayOf(intArrayOf(0,0,0),
                    intArrayOf(0,1,0),
                    intArrayOf(0,0,0))),

    EDGE_DETECTION(arrayOf( intArrayOf(1, 0, -1),
                            intArrayOf(0, 0, 0),
                            intArrayOf(-1, 0, 1))),

    SHARPEN(arrayOf(intArrayOf( 0, -1, 0),
                    intArrayOf(-1, 5, -1),
                    intArrayOf( 0, -1, 0)))
}