# CompressionTools

## Overview
`CompressionTools` is a Java project that provides tools for working with ZIP and 7z archive files. It includes classes for compressing, decompressing, and managing archive files, and provides a variety of methods to handle different formats and structures of compressed files. The project is useful for developers who need to integrate compression and decompression functionality into their Java applications.

## Project Components
The project consists of the following components:

### 1. ZipTool
The `ZipTool` class provides functionalities for handling ZIP files. It uses libraries like `zip4j` to facilitate compressing and decompressing files and directories, and managing entries in ZIP archives.

### 2. CompressArchiveStructure
`CompressArchiveStructure` is responsible for generating a predefined structure for compression. It is used for creating sample files and directories, making it easier to test and manage archive structures.

### 3. CompressNonGeneric7z
The `CompressNonGeneric7z` class is used for creating 7z archives. It makes use of the `sevenzipjbinding` library to compress files into the 7z format, providing high compression efficiency.

### 4. CompressNonGenericZip
Similar to `CompressNonGeneric7z`, the `CompressNonGenericZip` class uses the `sevenzipjbinding` library to create ZIP archives, allowing for versatile compression options.

### 5. SevenZipTool
The `SevenZipTool` class offers additional methods for compressing and managing files in the 7z format, including methods for compressing single files and creating complex archive structures.

## Features
- **ZIP and 7z Compression**: Compress files and directories into ZIP and 7z formats.
- **Decompression**: Extract files from ZIP archives.
- **Archive Management**: Manage files within archives, including listing entries, adding, and removing files.
- **Custom Archive Structure**: Create custom archive structures for testing or specific use cases.

## Installation
To use `CompressionTools`, include the source files in your Java project. The classes depend on external libraries like `zip4j` and `sevenzipjbinding`. Make sure to include these dependencies in your project.

## Usage
Here are some examples of how you can use the `CompressionTools` project:

### Compress Files into ZIP
```java
ZipTool zipTool = new ZipTool();
zipTool.compressDirectory("/path/to/source", "/path/to/target.zip");
```
This code snippet compresses the contents of a directory into a ZIP file using `ZipTool`.

### Compress Single File into 7z
```java
File sourceFile = new File("/path/to/source.txt");
File target7z = new File("/path/to/target.7z");
SevenZipTool.zipSingleFile(sourceFile, target7z);
```
This snippet compresses a single file into a 7z archive using `SevenZipTool`.

### Create Archive Structure
```java
Item[] archiveItems = CompressArchiveStructure.create();
// Use the created items for compression or other purposes
```
This snippet creates a sample archive structure using `CompressArchiveStructure`.

## Classes Summary
1. **`ZipTool`**: Handles ZIP file operations such as compressing directories and managing entries.
    - **Methods**: `compressDirectory()`, `decompressZipFile()`

2. **`CompressArchiveStructure`**: Creates a predefined structure of files and directories for compression.
    - **Methods**: `create()`

3. **`CompressNonGeneric7z`**: Handles 7z compression using `sevenzipjbinding`.
    - **Methods**: `compressTo7z()`

4. **`CompressNonGenericZip`**: Handles ZIP compression using `sevenzipjbinding`.
    - **Methods**: `compressToZip()`

5. **`SevenZipTool`**: Provides additional functionality for managing 7z archives, including single file compression.
    - **Methods**: `zipSingleFile()`

## Contributing
Feel free to contribute to the `CompressionTools` project by forking the repository, making changes, and submitting a pull request. Contributions for bug fixes, new features, and optimizations are always appreciated.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

