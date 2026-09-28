Git Project:

Methods and Functions:
- init() --- initializes folder structure: git/, objects/, HEAD, Index.
- hash(String filePath) --- Hashes file contents using SHA-1
- add(String filePath) --- Turns file into BLOB with hash name and inserts into git/objects/, and records in git/INDEX
