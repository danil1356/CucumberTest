Feature: 3Disk

  Scenario: 3Disk
    Given i am on the disk
    And selected file
    And the download button is pressed
    And load image on disk
    Then check the file
