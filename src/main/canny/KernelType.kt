package main.canny

enum class KernelType(val kernelMatrix: IntArray) {

    IDENTITY(
        intArrayOf(
            0, 0, 0,
            0, 1, 0,
            0, 0, 0
        )
    ),

    EDGE_DETECTION_1(
        intArrayOf(
            1, 0, -1,
            0, 0, 0,
            -1, 0, 1
        )
    ),

    EDGE_DETECTION_2(
        intArrayOf(
            0, 1, 0,
            1, -4, 1,
            0, 1, 0
        )
    ),

    EDGE_DETECTION_3(
        intArrayOf(
            -1, -1, -1,
            -1, 8, -1,
            -1, -1, 1
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