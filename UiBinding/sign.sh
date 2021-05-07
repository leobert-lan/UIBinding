#gpg --recipient 74A64469 --output test_en.txt --encrypt test.txt

#gpg --recipient 9C3650C1 --output ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.pom.asc --encrypt ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.pom
#
#gpg --recipient 9C3650C1 --output ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.aar.asc --encrypt ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.aar
#gpg --recipient 9C3650C1 --output ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2-sources.jar.asc --encrypt ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2-sources.jar


#--clearsign

gpg --recipient 9C3650C1 --clearsign ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.pom

gpg --recipient 9C3650C1 --clearsign ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2.aar
gpg --recipient 9C3650C1 --clearsign ../local_repo/io/github/leobert-lan/uibinding/0.0.2/uibinding-0.0.2-sources.jar
