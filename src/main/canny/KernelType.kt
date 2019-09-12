package main.canny

enum class KernelType(val kernelMatrix: IntArray) {

    IDENTITY(
        intArrayOf(
            0, 0, 0,
            0, 1, 0,
            0, 0, 0
        )
    ),

    EDGE_DETECTION_X(
        intArrayOf(
            1, 0, -1,
            0, 0, 0,
            -1, 0, 1
        )
    ),

    SHARPEN(
        intArrayOf(
            0, -1, 0,
            -1, 5, -1,
            0, -1, 0
        )
    )
}